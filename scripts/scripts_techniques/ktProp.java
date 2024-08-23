package scripts_techniques;

public class ktProp {
    
private String PropName;
private String PropValue;

    public ktProp(String propname, String propValue){
        PropName=propname;
        PropValue=propValue;

    }

    public String getPropName() {
        return this.PropName;
    }

    public String getPropValue() {
        return this.PropValue;
    }

    public void setPropValue(String propValue) {
        PropValue = propValue;
    }
}
