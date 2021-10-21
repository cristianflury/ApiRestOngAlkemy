package org.alkemy.somosmas.service.bo;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageBO<T> {

    public static final String PAGE = "?page=";
    private List<T> content;
    private String next;
    private String previous;


    public static <T>PageBO<T> build(Page<T> page, String url){
        PageBO<T> pageBO = new PageBO<>();
        pageBO.content = page.getContent();

        if(page.hasNext()) {
            pageBO.next = url +
                    PAGE +
                    (page.getNumber() + 1);
        }
        if(page.hasPrevious()) {
            pageBO.previous = url +
                    PAGE +
                    (page.getNumber() - 1);
        }
        return pageBO;
    }
}
