package dragongame;


// Dragon ärver från Enemy-klassen
public class Dragon extends Enemy {


    // Konstruktor för Dragon
    // Tar emot namn, hälsa, attackstyrka och beskrivning
    // och skickar vidare dessa till Enemy-konstruktorn
    public Dragon(String name, int health, int attackPower, String description) {
        super(name, health, attackPower, description);
    }

     // Metod som skriver ut en ASCII-bild av en drake i konsolen
    public void printAsciiDragon() {
        System.out.println(
        "                                                  .~))>>\n"+
        "                                                 .~)>>\n"+
        "                                               .~))))>>>\n"+
        "                                             .~))>>             ___\n"+
        "                                           .~))>>)))>>      .-~))>>\n"+
        "                                         .~)))))>>       .-~))>>)>\n"+
        "                                       .~)))>>))))>>  .-~)>>)>\n"+
        "                   )                 .~))>>))))>>  .-~)))))>>)>\n"+
        "                ( )@@*)             //)>))))))  .-~))))>>)>\n"+
        "              ).@(@@               //))>>))) .-~))>>)))))>>)>\n"+
        "            (( @.@).              //))))) .-~)>>)))))>>)>\n"+
        "          ))  )@@*.@@ )          //)>))) //))))))>>))))>>)>\n"+
        "       ((  ((@@@.@@             |/))))) //)))))>>)))>>)>\n"+
        "      )) @@*. )@@ )   (\\_(\\-\\b  |))>)) //)))>>)))))))>>)>\n"+
        "    (( @@@(.@(@ .    _/`-`  ~|b |>))) //)>>)))))))>>)>\n"+
        "     )* @@@ )@*     (@)  (@) /\\b|))) //))))))>>))))>>\n"+
        "   (( @. )@( @ .   _/  /    /  \\b)) //))>>)))))>>>_._\n"+
        "    )@@ (@@*)@@.  (6///6)- / ^  \\b)//))))))>>)))>>   ~~-.\n"+
        " ( @jgs@@. @@@.*@_ VvvvvV//  ^  \\b/)>>))))>>      _.     `bb\n"+
        " ((@@ @@@*.(@@ . - | o |' \\ (  ^   \\b)))>>        .'       b`,\n"+
        "   ((@@).*@@ )@ )   \\^^^/  ((   ^  ~)_        \\  /           b `,\n"+
        "     (@@. (@@ ).     `-'   (((   ^    `\\ \\ \\ \\ \\|             b  `.\n"+
        "       (*.@*              / ((((        \\| | |  \\       .       b `.\n"+
        "                         / / (((((  \\    \\ /  _.-~\\     Y,      b  ;\n"+
        "                        / / / (((((( \\    \\.-~   _.`\" _.-~`,    b  ;\n"+
        "                       /   /   `(((((()    )    (((((~      `,  b  ;\n"+
        "                     _/  _/      `\"\"\"/   /'                  ; b   ;\n"+
        "                 _.-~_.-~           /  /'                _.'~bb _.'\n"+
        "               ((((~~              / /'              _.'~bb.--~\n"+
        "                                  ((((          __.-~bb.-~\n"+
        "                                              .'  b .~~\n"+
        "                                              :bb ,' \n"+
        "                                              ~~~~\n"); 
}

// Överskuggar attack-metoden från Enemy
    // Draken attackerar spelaren med eld
    @Override
    public void attack(Player player) {
        System.out.println(getName() + " breathes fire at you, dealing " + getAttackPower() + " damage!");
        player.takeDamage(getAttackPower());
    }


    // Överskuggar takeDamage-metoden från Enemy
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (!isAlive()) {
            System.out.println("You have defeated the " + getName() + "! Where do you want to go next?");
        }
    }
}
