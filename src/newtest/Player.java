/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newtest;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Patrick
 */
public class Player {
    List<Units> units = new ArrayList<>();
    public Player(){ 
        addUnits();
    }
    public void addUnits(){
        Units unitToAdd;
        unitToAdd=new newtest.units.archer();
        unitToAdd.setUnits(1, 1);
        units.add(unitToAdd);
        unitToAdd=new newtest.units.mage();
        unitToAdd.setUnits(2, 2);
        units.add(unitToAdd);
    }
    public Units[] unitList(){
        Units [] array = units.toArray( new Units[ units.size() ] );
        return array;
    }
}
