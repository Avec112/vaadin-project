package io.avec.vaadimbuilderdemo.views.person;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import io.avec.vaadimbuilderdemo.data.entity.Department;
import io.avec.vaadimbuilderdemo.data.entity.Person;
import io.avec.vaadimbuilderdemo.data.service.PersonService;
import io.avec.vaadimbuilderdemo.views.main.MainView;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.vaadin.artur.helpers.CrudServiceDataProvider;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Route(value = "person", layout = MainView.class)
@PageTitle("Person")
@CssImport("./styles/views/personview/person-view.css")
@RouteAlias(value = "person", layout = MainView.class)
public class PersonView extends Div {
    private final Grid<Person> grid;
    private final TextField firstName = new TextField("First name");
    private final TextField lastName = new TextField("Last name");
    private final TextField email = new TextField("Email");
    private final TextField phone = new TextField("Phone");
    private final DatePicker dateOfBirth = new DatePicker("Date of birth");
    private final ComboBox<Department> departmentCombo = new ComboBox<>("Department");



    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    private final Binder<Person> binder;

    private Person person = new Person();

    public PersonView(PersonService personService) {
        setId("person-view");

        // populate departmentCombo
        departmentCombo.setDataProvider(
                personService.getDepartmentService()::fetch,
                personService.getDepartmentService()::count);

        // Configure Grid
        grid = new Grid<>(Person.class);
        grid.setColumns("firstName", "lastName", "email", "phone", "dateOfBirth", "department.departmentName"); // lookup by reflection
        grid.getColumns().forEach(column -> column.setAutoWidth(true));
        grid.setDataProvider(new CrudServiceDataProvider<Person, Void>(personService));
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Optional<Person> personFromBackend = personService.get(event.getValue().getId());
                // when a row is selected but the data is no longer available, refresh grid
                if (personFromBackend.isPresent()) {
                    populateForm(personFromBackend.get());
                } else {
                    refreshGrid();
                }
            } else {
                clearForm();
            }
        });

        // Define mandatory fields
        firstName.setRequired(true);
        departmentCombo.setRequired(true);

        // Configure Form
        binder = new Binder<>(Person.class);
        setupBinding();

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.person == null) {
                    Notification.show("Please add some values.");
                }
                binder.writeBean(this.person);
                personService.update(this.person);
                clearForm();
                refreshGrid();
                Notification.show("Person details stored.");
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the person details.");
            }
        });

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);
    }

    private void setupBinding() {
        // TODO binding
        binder.forField(firstName)
                .withValidator(StringUtils::isNoneBlank, "First name must be set.")
                .bind(Person::getFirstName, Person::setFirstName);
        binder.forField(departmentCombo)
                .withValidator(Objects::nonNull, "Department must be set.")
                .bind(person -> {
                    if(person.getDepartment() != null) {
                        return person.getDepartment();
                    }
                    return new Department();

                },
                (person, department) -> {
//                    Department department = person.getDepartment();
                    if(department != null) {
                        log.debug("{} reassigned to department {}", person.getFirstName(), department);
                        person.setDepartment(department);
                    }
                }
        );

        // Bind the other fields. No validation done here and must be done earlier.
        binder.bindInstanceFields(this);
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setId("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setId("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        AbstractField[] fields = new AbstractField[] {firstName, lastName, email, phone, dateOfBirth, departmentCombo/*, departmentField*/};
        for (AbstractField field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(fields);
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setId("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
        save.addClickShortcut(Key.ENTER);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Person value) {
        this.person = value;
        binder.readBean(this.person);
    }
}
