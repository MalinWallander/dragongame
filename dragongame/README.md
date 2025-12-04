#Projektbeskrivning

    Det här projektet är ett textbaserat äventyrsspel skapat i Java. Spelaren utforskar en gammal ruin, Eldrath’s Castle, ett drakförstört slott med flera rum som kan nås genom att navigera i väderstreck (north, south, east, west).
    Spelets första del fokuserar på navigering, rumsbeskrivningar och spelstruktur, inte strid eller föremål.

    Projektet följer uppgiftens krav på objektorienterad design och använder ett antal klasser som samarbetar för att skapa en fungerande spelvärld.

#Spelvärld & Designidé

    Spelet utspelar sig i en fantasy-miljö där spelaren stiger in i ruinen av det gamla drakslottet Eldrastens Ruin. Rummen är designade för att ge stämning, tydlig navigering och möjlighet till framtida utbyggnad (strider, föremål, NPC:er).

    Spelet innehåller följande rum:

    | Rum             | Tillgängliga riktningar                                               |
    | --------------- | --------------------------------------------------------------------- |
    | Start           | forward → Entrance                                                    |
    | Entrance        | east → Great Hall, south → Library, up (locked) → Watch Tower                  |
    | Great Hall      | west → Entrance, north → Kitchen, south → Armory, east → Throne Hall  |
    | Library         | north → Entrance, east → Armory                                       |
    | Armory          | north → Great Hall, west → Library, up → High Tower, down → Catacombs |
    | Kitchen         | south → Great Hall, east (locked) → Throne Hall                                |
    | Throne Hall     | west → Kitchen, down → Catacombs                                      |
    | Watch Tower     | down → Entrance                                                       |
    | High Tower      | down → Armory                                                         |
    | Prison Chambers | south → Sorcery Chamber, west → Catacombs                             |
    | Sorcery Chamber | north → Prison Chambers, east → Exit                                  |
    | Catacombs       | north → Throne Hall, east → Prison Chambers, west → Armory            |
    | Exit            | –                                                                     |

    	Alla rum har:
    	--en unik beskrivning (doNarrative())
    	--ett antal dörrar riktade åt väderstreck
    	--referenser till vilka rum dörrarna leder till

#Datastrukturer

    	--Rumslista: ArrayList<Room> Anledningen till ArrayList<Room> och inte en vanlig array är att vi vill kunna utveckla spelet och att det ska vara mer dynamiskt. vi är medvetna om att en array tar mindre plats och kan göra spelet långsammare men vi tycker att fördelarna med en array list överväger.
    	--Dörrar: HashMap<String, Door> i varje rum
    	--Riktningar: Map<String, String> directionAliases tolkar korta och långa kommandon
    	--Spelaren: Player håller koll på nuvarande rum

#Klasser

    	--GameEngine: startar spelet, tolkar kommandon och flyttar spelaren
    	--GameWorld: skapar rum, dörrar och startpunkt
    	--Room: lagrar namn, beskrivning och dörrar; skriver ut rumsinfo
    	--Door: representerar dörrar vilket rum de leder till och om den är låst
    	--Player: håller koll på nuvarande rum och flyttar spelaren
    	--MainGame: kör playGame();

#Hur spelet fungerar

    	1- Start utanför slottet
    	2- Skriv f eller forward för att gå in i slottet
    	3- Varje rum visar beskrivning och möjliga riktningar
    	4- Spelaen skriver kommando som visar vart de vill gå.
    	5- Kommandon: north/n, south/s, east/e, west/w, up/u, down/d, forward/f
    	6- Om dörr finns och är inte är låst → flytta spelaren
    	7- Om ingen dörr eller dörr är låst → felmeddelande
    	8- När Exit nås avslutas spelet
    	9- Skriv stop när som helst för att avsluta

#Antaganden/designval:

    	--Map<String, String> directionAliases → gör spelet mer användarvänligt, spelaren kan skriva tex s eller south. Detta minskar risken för att systemet krachar.
    	--ArrayList för flexibilitet
    	--HashMap för dörrar → snabb åtkomst
    	--Textbaserad, fokus på OOP
    	--Engelska som spelspråk
    	--Rumsbeskrivningar är detaljerade för stämning

#Körning av spel:

    	Kör MainGame:

    	public class MainGame {
    			public static void main(String[] args) {
    					GameEngine game = new GameEngine();
    					game.playGame();
    			}
    	}

#Möjliga utbyggnader

    	--Stridssystem och bossar
    	--NPC:er och dialog
    	--Föremål och inventarie
    	--Låsta dörrar och nycklar
    	--ASCII-grafik eller JavaFX GUI
