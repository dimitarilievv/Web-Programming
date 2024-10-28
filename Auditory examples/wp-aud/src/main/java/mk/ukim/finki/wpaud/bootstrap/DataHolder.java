package mk.ukim.finki.wpaud.bootstrap;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.model.Manufacturer;
import mk.ukim.finki.wpaud.model.Product;
import mk.ukim.finki.wpaud.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Category> categoryList = new ArrayList<>();
    public static List<User> users=new ArrayList<>();
    public static List<Manufacturer> manufacturers=new ArrayList<>();
    public static List<Product> products=new ArrayList<>();
    @PostConstruct //when dataholder component is called to be called init immediately
    public void init(){
        this.categoryList.add(new Category("Software","Software Category"));
        this.categoryList.add(new Category("Books","Books Category"));

        this.users.add(new User("dimitar.iliev","di","Dimitar","Iliev"));
        this.users.add(new User("kostadin.mishev","ki","Kostadin","Mishev"));

        Manufacturer manufacturer=new Manufacturer("Nike","NY NY");
        manufacturers.add(manufacturer);
        Category category=new Category("Sport","Sport category");
        categoryList.add(category);
        products.add(new Product("Ball",235.8,7,category,manufacturer));
        products.add(new Product("Ball 2 ",100.2,7,category,manufacturer));
        products.add(new Product("Ball 3",45.1,7,category,manufacturer));
    }

}