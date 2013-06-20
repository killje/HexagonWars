/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class NewUIAction extends UIAction {

    protected UserInterface ui;

    public NewUIAction(String uiName) {
        ui = new UserInterface(uiName);
    }
    
    public void addIcon(String iconName,UIAction action){
        ui.addAction(iconName,action);
    }
}
