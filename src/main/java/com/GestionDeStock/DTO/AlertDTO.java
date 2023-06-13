package com.GestionDeStock.DTO;


import com.GestionDeStock.Entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertDTO {

    private int idalert ;

    private int quanityMuni;

    private int quanityMax;

    private ArticleDTO article;
}
