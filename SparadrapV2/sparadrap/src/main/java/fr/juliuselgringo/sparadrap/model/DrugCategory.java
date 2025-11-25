package fr.juliuselgringo.sparadrap.model;

import fr.juliuselgringo.sparadrap.DAO.DrugCategoryDAO;
import fr.juliuselgringo.sparadrap.utility.Gui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * class permettant de gérer les différentes catégories de médicament
 */
public class DrugCategory {

    private Integer categoryId;
    private String categoryName;

    /**
     * constructeur par défaut
     */
    public DrugCategory() {}

    /**
     * constructeur
     * @param categoryId Integer
     * @param categoryName String
     */
    public DrugCategory(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    /**
     * getter categoryId
     * @return Integer
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * setter categoryId
     * @param categoryId Integer
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * getter categoryName
     * @return String
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * setter categoryName
     * @param categoryName String
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * to string de la catégorie de médicament
     * @return String
     */
    @Override
    public String toString() {
        return this.categoryName;
    }


}
