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

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.validator.DateRangeValidator;
import com.vaadin.flow.data.validator.IntegerRangeValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.gmail.anamara.backend.Category;
import com.gmail.anamara.backend.CategoryService;
import com.gmail.anamara.backend.Review;
import com.gmail.anamara.ui.common.AbstractEditorDialog;

/**
 * A dialog for editing {@link Review} objects.
 */
public class ReviewEditorDialog extends AbstractEditorDialog<Review> {

    private transient CategoryService categoryService = CategoryService
            .getInstance();

    private ComboBox<Category> categoryBox = new ComboBox<>();
    private ComboBox<String> scoreBox = new ComboBox<>();
    private DatePicker lastTasted = new DatePicker();
    private TextField beverageName = new TextField();
    private TextField timesTasted = new TextField();

    public ReviewEditorDialog(BiConsumer<Review, Operation> saveHandler,
            Consumer<Review> deleteHandler) {
        super("review", saveHandler, deleteHandler);

        createNameField();
        createCategoryBox();
        createDatePicker();
        createTimesField();
        createScoreBox();
    }

    private void createScoreBox() {
        scoreBox.setLabel("Conductor");
        scoreBox.setRequired(false);
        scoreBox.setAllowCustomValue(false);
        scoreBox.setItems("Didier Salazar", "Cesar Ortiz", "Deborah Moscoso", "Mauricio Londoño", "Otro");
        getFormLayout().add(scoreBox);


    }

    private void createDatePicker() {
        lastTasted.setLabel("Fecha del servicio: ");
        lastTasted.setRequired(true);
        
        lastTasted.setMin(LocalDate.now());
        lastTasted.setValue(LocalDate.now());
        getFormLayout().add(lastTasted);

      
    }

    private void createCategoryBox() {
        categoryBox.setLabel("Tipo");
        categoryBox.setRequired(true);
        categoryBox.setItemLabelGenerator(Category::getName);
        categoryBox.setAllowCustomValue(false);
        categoryBox.setItems(categoryService.findCategories(""));
        getFormLayout().add(categoryBox);

        getBinder().forField(categoryBox)
                .withValidator(Objects::nonNull,
                        "The category should be defined.")
                .bind(Review::getCategory, Review::setCategory);
    }

    private void createTimesField() {
        timesTasted.setLabel("ID Contrato o Convenio");
        timesTasted.setRequired(false);
        timesTasted.setPattern("[0-9]*");
        timesTasted.setPreventInvalidInput(true);
        timesTasted.setValue("19012019_FUEC_CONTRATO_F000159");
        timesTasted.setTitle("19012019_FUEC_CONTRATO_F000159");

        
        getFormLayout().add(timesTasted);

    }

    private void createNameField() {
        beverageName.setLabel("Contratante / NIT ");
        beverageName.setRequired(true);
        getFormLayout().add(beverageName);

        getBinder().forField(beverageName)
                .withConverter(String::trim, String::trim)
                .withValidator(new StringLengthValidator(
                        "El nombre debe ser nombre completo. NIT o CC.",
                        9, null))
                .bind(Review::getName, Review::setName);
    }

    @Override
    protected void confirmDelete() {
        openConfirmationDialog("Eliminar",
                "Está seguro que desea eleminar el elemento seleccionado  “" + getCurrentItem().getName() + "”?", "");
    }

}
