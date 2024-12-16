package mk.finki.ukim.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.lab.model.Event;
import mk.finki.ukim.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "event-servlet",urlPatterns = "/eventServlet")
public class EventListSevlet extends HttpServlet {
    private final EventService eventService;
    private final SpringTemplateEngine springTemplateEngine;

    public EventListSevlet(EventService eventService, SpringTemplateEngine springTemplateEngine) {
        this.eventService = eventService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        List<Event> events=null;
        String searchText = req.getParameter("searchText");
        String minRatingParam = req.getParameter("minRating");

        if ((searchText == null || searchText.isEmpty()) && (minRatingParam == null || minRatingParam.isEmpty())) {
            events = eventService.listAll();
        } else {
            double minRating = minRatingParam != null && !minRatingParam.isEmpty() ? Double.parseDouble(minRatingParam) : 0.0;
            events = eventService.searchEvents(searchText, minRating);
        }


        context.setVariable("events",events);
        context.setVariable("ipAddress",req.getRemoteAddr());
        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
