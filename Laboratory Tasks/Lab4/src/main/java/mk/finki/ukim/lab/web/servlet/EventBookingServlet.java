package mk.finki.ukim.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.lab.model.EventBooking;
import mk.finki.ukim.lab.repository.inmemory.InMemoryEventBookingRepository;
import mk.finki.ukim.lab.service.EventBookingService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "event-booking-servlet",urlPatterns = "/eventBookingServlet")
public class EventBookingServlet extends HttpServlet {
    private final EventBookingService eventBookingService;
    private final InMemoryEventBookingRepository repo;
    private final SpringTemplateEngine springTemplateEngine;

    public EventBookingServlet(EventBookingService eventBookingService, InMemoryEventBookingRepository repo, SpringTemplateEngine springTemplateEngine) {
        this.eventBookingService = eventBookingService;
        this.repo = repo;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        String eventName=req.getParameter("eventName");
        String attendeeName=req.getParameter("attendeeName");
        String attendeeAddress = req.getRemoteAddr();
        int numberOfTickets = Integer.parseInt(req.getParameter("numTickets"));

        EventBooking eventBooking=eventBookingService.placeBooking(eventName,attendeeName,attendeeAddress,numberOfTickets);
        repo.bookings.add(eventBooking);

        List<EventBooking> allbookings =eventBookingService.filterBookings(attendeeName);

        context.setVariable("booking",eventBooking);
        context.setVariable("allbookings",allbookings);

        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());
    }
}
