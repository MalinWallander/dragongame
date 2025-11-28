Projektbeskrivning

Det här projektet är ett textbaserat äventyrsspel skapat i Java. Spelaren utforskar en gammal ruin, Eldrath’s Castle, ett drakförstört slott med flera rum som kan nås genom att navigera i väderstreck (north, south, east, west).
Spelets första del fokuserar på navigering, rumsbeskrivningar och spelstruktur, inte strid eller föremål.

Projektet följer uppgiftens krav på objektorienterad design och använder ett antal klasser (Game, Room, Door, Player) som samarbetar för att skapa en fungerande spelvärld.



Spelvärld & Designidé:

Spelet utspelar sig i en fantasy-miljö där spelaren stiger in i ruinen av det gamla drakslottet Eldrastens Ruin. Rummen är designade för att ge stämning, tydlig navigering och möjlighet till framtida utbyggnad (strider, föremål, NPC:er).


Spelet innehåller följande rum:

Castle Courtyard

Great Hall

Old Armory

Ancient Library

Prison Dungeon

Sorcery Chamber

High Tower

Throne Hall

Dragon Catacombs

Alla rum har:

en unik beskrivning (doNarrative())

ett antal dörrar riktade åt väderstreck

referenser till vilka rum dörrarna leder till



Datastrukturer:

Alla rum skapas i metoden setupGame() och lagras i en ArrayList<Room>. Detta gör det enkelt att:

bygga kartor,

lägga till fler rum,

iterera över alla rum vid behov.

Klasser & Ansvar


Game:

Hanterar uppstart (setupGame())

Skapar rum, dörrar och spelaren

Innehåller spel-loopen (playGame())


Room:

Har privat instansdata: namn, beskrivning och en lista av dörrar

Metoden doNarrative() skriver ut rummets information

Getters & setters för all åtkomlig data


Door:

Innehåller riktning (north/south/east/west)

Refererar till ett rum som dörren leder till

Har getter/setters enligt krav


Player:

Innehåller aktuell position (nuvarande Room)

Har metoder för att flytta mellan rum via dörrar

Alla variabler är privata enligt uppgiftskraven, och åtkomst sker via getters/setters.


Hur spelet fungerar:

Spelet startar i Castle Courtyard.

doNarrative() beskriver rummet och vilka riktningar som är möjliga.

Spelaren skriver ett väderstreck (n, s, e, w).

Om en dörr finns i den riktningen → spelaren flyttar till det nya rummet.

Annars visas ett felmeddelande.

Spelet fortsätter tills spelaren väljer att avsluta.




Antaganden: 

Här beskriver du de val du gjorde som inte stod i uppgiften. Exempel:

Jag valde att lägga till mer detaljerade rumsbeskrivningar eftersom det gjorde spelvärlden mer engagerande.

Jag lade till fler rum (9 rum istället för 5–6) för att kartan skulle kännas mer som en riktig dungeon.

Jag valde att lagra dörrar i en HashMap<String, Door> för snabb åtkomst via riktningar (om detta är sant för din implementation).

Jag valde att lägga till  Map<String, String> directionAliases för att göra spelet mer effektivt för spelaren om den gör ett kommando som endast liknar de fyra väderstrecken. Detta minskar risken för att systemet krachar och även mer användarvänligt/utesluter felmeddelanden.

Jag valde att använda engelska som spelspråk trots svensk uppgiftsbeskrivning.

Jag valde att göra spel-loopen textbaserad utan GUI för att fokusera på objektorienterad design.



Körning av spel: 

Spelet körs genom att starta klassen Game och anropa:

Game game = new Game();
game.setupGame();
game.playGame();

Möjliga utbyggnader (framtida arbete)

Stridssystem

Föremål (keys, potions, weapons)

Inventariehantering

En riktig "boss encounter" i Dragon Catacombs

Låsta dörrar och nycklar

Ljud/ASCII-grafik

NPC:er och dialog
