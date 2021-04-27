package org.openjfx.view_model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class ViewModelSearch {
    private final ViewModelTours viewModelTours;


    public void searchLists( String search) {
        if(search!=null) {
            viewModelTours.getSearchedTours().setPredicate(t -> t.getTourName().toLowerCase().contains(search.toLowerCase()));
        }
    }
}
