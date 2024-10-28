package mk.ukim.finki.wpaud.repository;

import mk.ukim.finki.wpaud.bootstrap.DataHolder;
import mk.ukim.finki.wpaud.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryCategoryRepository{
    public List<Category> findAll(){
        return DataHolder.categoryList;
    }

    public Category save(Category c){
        if(c==null || c.getName()==null || c.getName().isEmpty()){
            return null;
        }
        DataHolder.categoryList.removeIf(r->r.getName().equals(c.getName()));
        DataHolder.categoryList.add(c);
        return c;
    }

    public Optional<Category> findByName(String name){
        return DataHolder.categoryList
                .stream()
                .filter(r->r.getName().equals(name))
                .findFirst();
    }
    public List<Category> search(String text){
        return DataHolder.categoryList
                .stream()
                .filter(r->r.getName().contains(text) || r.getDescription().contains(text))
                .collect(Collectors.toList());
    }
    public void delete(String name){
        if(name==null){
            return;
        }
        DataHolder.categoryList.removeIf(r->r.getName().equals(name));
    }
}
