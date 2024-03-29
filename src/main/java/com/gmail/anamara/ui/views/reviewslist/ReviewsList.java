/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.gmail.anamara.ui.views.reviewslist;

import java.util.List;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.ModelItem;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.gmail.anamara.backend.Review;
import com.gmail.anamara.backend.ReviewService;
import com.gmail.anamara.ui.MainLayout;
import com.gmail.anamara.ui.common.AbstractEditorDialog;
import com.gmail.anamara.ui.encoders.LocalDateToStringEncoder;
import com.gmail.anamara.ui.encoders.LongToStringEncoder;
import com.gmail.anamara.ui.views.reviewslist.ReviewsList.ReviewsModel;

/**
 * Displays the list of available categories, with a search filter as well as
 * buttons to add a new category or edit existing ones.
 *
 * Implemented using a simple template.
 */
@Route(value = "", layout = MainLayout.class)
@PageTitle("eFUEC CAR MII")
@Tag("reviews-list")
@HtmlImport("frontend://src/views/reviewslist/reviews-list.html")
public class ReviewsList extends PolymerTemplate<ReviewsModel> {

    public interface ReviewsModel extends TemplateModel {
        @Encode(value = LongToStringEncoder.class, path = "id")
        @Encode(value = LocalDateToStringEncoder.class, path = "date")
        @Encode(value = LongToStringEncoder.class, path = "category.id")
        void setReviews(List<Review> reviews);
    }

    @Id("search")
    private TextField search;
    @Id("newReview")
    private Button addReview;
    @Id("header")
    private H2 header;

    private ReviewEditorDialog reviewForm = new ReviewEditorDialog(
            this::saveUpdate, this::deleteUpdate);

    public ReviewsList() {
        search.setPlaceholder("Buscar FUEC");
        search.addValueChangeListener(e -> updateList());
        search.setValueChangeMode(ValueChangeMode.EAGER);

        addReview.addClickListener(e -> openForm(new Review(),
                AbstractEditorDialog.Operation.ADD));

        // Set review button and edit button text from Java
        
        // New review --> Nuevo FUEC
        getElement().setProperty("reviewButtonText", "Nuevo FUEC");
        getElement().setProperty("editButtonText", "Editar");

        updateList();

    }

    public void saveUpdate(Review review,
            AbstractEditorDialog.Operation operation) {
        ReviewService.getInstance().saveReview(review);
        updateList();
        Notification.show(
                "Elemento agregado. Operación exitosa. " + operation.getNameInText() + "ed.",
                3000, Position.BOTTOM_START);
    }

    public void deleteUpdate(Review review) {
        ReviewService.getInstance().deleteReview(review);
        updateList();
        Notification.show("Elemento eliminado. Operación exitosa.", 3000,
                Position.BOTTOM_START);
    }

    private void updateList() {
        List<Review> reviews = ReviewService.getInstance()
                .findReviews(search.getValue());
        if (search.isEmpty()) {
            header.setText("Extracto de Contrato (FUEC)");
            header.add(new Span(reviews.size() + " en total"));
        } else {
            header.setText("Buscar “" + search.getValue() + "”");
            if (!reviews.isEmpty()) {
                header.add(new Span(reviews.size() + " resultados"));
            }
        }
        getModel().setReviews(reviews);
    }

    @EventHandler
    private void edit(@ModelItem Review review) {
        openForm(review, AbstractEditorDialog.Operation.EDIT);
    }

    private void openForm(Review review,
            AbstractEditorDialog.Operation operation) {
        // Add the form lazily as the UI is not yet initialized when
        // this view is constructed
        if (reviewForm.getElement().getParent() == null) {
            getUI().ifPresent(ui -> ui.add(reviewForm));
        }
        reviewForm.open(review, operation);
    }

}
