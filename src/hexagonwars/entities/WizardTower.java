package hexagonwars.entities;

import hexagonwars.ProduceAction;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class WizardTower extends Producer {

    public WizardTower(int playerColor) {
        super(playerColor);
        this.startHealth = 350;
        this.health = this.startHealth;
    }
    
    @Override
    public void addUIAfterFinish() {
        ProduceAction produceWizard = new ProduceAction(this, new Wizard(playerColor));
        addUIElement("SmallWizard", produceWizard);

    }
}

