package pl.minda.carapplication;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MarkGui extends VerticalLayout {

    private CarDao carDao;

    private TextField textFieldBrand;
    private TextArea textArea;

    private Button buttonFind;

    @Autowired
    public MarkGui(CarDao carDao) {
        this.carDao = carDao;
        this.textFieldBrand = new TextField("Marka:");
        this.textArea = new TextArea();
        this.buttonFind = new Button("Find");

        buttonFind.addClickListener(buttonClickEvent -> textArea.setValue(carDao.findByBrand(textFieldBrand.getValue()).toString()));
        add(textFieldBrand,textArea,buttonFind);
    }
}
