package scripts_techniques;
import java.util.List;

public class ktObject {

    private String label;
    private String techlabel;
    private String screenLabel;
    private String screenTechLabel;
    private List <ktProp> Props;

    public ktObject(String lab, String techlab, List <ktProp> Propslist){
        label = lab;
        techlabel = techlab;
        Props = Propslist;

    }
    public String getLabel() {
        return this.label;
    }

    public String getTechLabel() {
        return this.techlabel;
    }

    public String getScreenLabel() {
        return this.screenLabel;
    }

    public String getScreenTechLabel() {
        return this.screenTechLabel;
    }

    public List <ktProp> getListOfProps() {
        return this.Props;
    }

    public void setListOfProps (List <ktProp> listOfProps) {
        this.Props = listOfProps;
    }
}
