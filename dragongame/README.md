# Projektbeskrivning

Det här projektet är ett textbaserat äventyrsspel skapat i Java. Spelaren utforskar ruinerna av ett gammalt drakslott fyllt med låsta dörrar, fiender och föremål. Målet är att hitta den legendariska skatten och ta sig ut ur slottet levande.

Spelet är helt konsolbaserat och fokuserar på objektorienterad design. Förutom navigering och rumsbeskrivningar innehåller spelet nu även stridssystem, inventory, låsta dörrar, nycklar och hälsa.

Projektet följer uppgiftens krav på objektorienterad programmering och använder flera samverkande klasser för att bygga en fungerande spelvärld.

---

# Spelvärld & Designidé

Spelet utspelar sig i en fantasy-miljö där spelaren kliver in i ruinerna av ett gammalt slott. Slottet består av flera rum som är sammankopplade via dörrar i olika riktningar.

Alla rum har:

- En unik beskrivning (`roomNarrative()`)
- Ett antal dörrar kopplade till riktningar
- Referenser till vilka rum dörrarna leder till
- Möjlighet att innehålla föremål och fiender

Vissa rum innehåller:

- Föremål som kan plockas upp
- Fiender som måste besegras
- Låsta dörrar som kräver nycklar

Rummen är designade för att ge stämning, tydlig navigering och möjlighet till framtida utbyggnad. Se planritning under resources.

Spelet innehåller följande rum:

| Rum             | Tillgängliga riktningar                                               |
| --------------- | --------------------------------------------------------------------- |
| Start           | forward → Entrance                                                    |
| Entrance        | east → Great Hall, south → Library, up (locked) → Watch Tower         |
| Great Hall      | west → Entrance, north → Kitchen, south → Armory, east → Throne Hall  |
| Library         | north → Entrance, east → Armory                                       |
| Armory          | north → Great Hall, west → Library, up → High Tower, down → Catacombs |
| Kitchen         | south → Great Hall, east (locked) → Throne Hall                       |
| Throne Hall     | west → Kitchen, down → Catacombs                                      |
| Watch Tower     | down → Entrance                                                       |
| High Tower      | down → Armory                                                         |
| Prison Chambers | south → Sorcery Chamber, west → Catacombs                             |
| Sorcery Chamber | north → Prison Chambers, east → Exit                                  |
| Catacombs       | north → Throne Hall, east → Prison Chambers, west → Armory            |
| Exit            | –                                                                     |

---

# Datastrukturer

- Dörrar:
  Varje rum använder en `HashMap<String, Door>` för att lagra dörrar och riktningar.
- Föremål:
  Föremål lagras i `ArrayList<Item>` både i rum och i spelarens inventory.
- Fiender:
  Fiender lagras i `ArrayList<Enemy>` i respektive rum.
- Riktningar:  
  `Map<String, String> directionAliases` används för att tolka både korta och långa kommandon, t.ex. `n` och `north`.
- Spelare:
  Klassen `Player` håller koll på nuvarande rum, hälsa och inventory.

---

# Klasser

- GameEngine
  Startar spelet, hanterar spel-loopen, tolkar kommandon, samt kontrollerar föremål och fiender i rum.
- GameWorld
  Skapar alla rum, kopplar dörrar, placerar föremål och fiender samt anger startposition.
- Room
  Innehåller namn, beskrivning, dörrar, föremål och fiender.
- Door  
  Representerar en dörr mellan två rum och kan vara låst eller olåst.
- Player
  Håller koll på nuvarande rum, hälsa och inventory. Kan röra sig mellan rum, attackera fiender och använda föremål.
- Enemy (abstrakt)
  Basklass för fiender. Implementeras av `Goblin` och `Dragon`.
- Item (abstrakt)
  Basklass för föremål. Implementeras av `Key`, `Potion`, `Sword` och `Treasure`.
- MainGame
  Innehåller `main`-metoden som startar spelet.

---

# Hur spelet fungerar

1. Spelet startar vid `Start`
2. Spelaren anger sitt namn
3. Skriv `f` eller `forward` för att gå in i slottet
4. Varje rum visar sin beskrivning
5. Spelaren navigerar med kommandon som `n`, `s`, `e`, `w`, `u`, `d`
6. Om en dörr är låst kan spelaren använda en nyckel
7. Föremål kan plockas upp och lagras i inventory
8. Fiender måste besegras i strid
9. Spelaren kan hela sig med health potions
10. När spelaren når `Exit` avslutas spelet

Spelaren kan när som helst skriva stop för att avsluta spelet manuellt.

---

# Motiverande av designval

## Objektorienterad design

Projektet är uppbyggt enligt objektorienterade principer där spelets centrala delar representeras av separata klasser med tydligt ansvar. Spelvärlden består av rum (Room) som är sammankopplade med dörrar (Door). Spelaren (Player) kan röra sig mellan rummen, interagera med föremål och bekämpa fiender. Alla instansvariabler är privata och nås via metoder, vilket följer god programmeringssed och inkapsling.

## Arv och polymorfism

För att uppfylla kraven på arv och polymorfism används abstrakta basklasser:

- Enemy är en abstrakt basklass för alla fiender
  -- Subklasser: Goblin, Dragon
  -- Varje fiende implementerar sin egen attack(Player)-metod

- Item är en abstrakt basklass för alla föremål
  -- Subklasser: Key, Potion, Sword, Treasure
  -- Varje item kan implementera egen funktionalitet via use(Player)

Polymorfism används genom att:

- Fiender lagras som Enemy men beter sig olika beroende på faktisk typ
- Föremål lagras som Item i spelarens inventory men utför olika handlingar

Detta gör det enkelt att lägga till nya fiender eller föremål utan att ändra befintlig kod.

## Spelmekanik och interaktion

- Spelaren har hälsa och attackstyrka
- Utan vapen gör spelaren 1 skada
- Ett svärd (Sword) ökar spelarens attackstyrka till 2
- Fiender attackerar spelaren tills någon besegras
- Hälsodryck (Potion) kan användas för att återställa hälsa
- Nycklar (Key) används för att låsa upp låsta dörrar

Strider sker turbaserat och använder samma metoder oavsett fiendetyp, vilket tydligt visar användningen av polymorfism.

## Motivering datastrukturer

- `directionAliases` används för bättre användarupplevelse
- `HashMap` används för snabb åtkomst till dörrar
- `ArrayList` används för inventory och fiender för flexibilitet

## ASCII-grafik

ASCII-grafik används för att förstärka spelupplevelsen:

- Draken skriver ut ASCII-grafik när striden startar
- Skatt visas när spelaren når slutet av spelet

ASCII-utskrifterna är placerade nära den logik de hör till, vilket håller koden organiserad och lättläst.

---

# Antaganden

- Spelaren kan bära obegränsat antal föremål
- Endast ett vapen (svärd) påverkar attackstyrka
- Alla strider måste genomföras för att kunna vinna spelet
- Textbaserat gränssnitt används enligt uppgiftens exempel

---

# Körning av spel

Spelet körs genom att starta klassen `MainGame`:

```java
public class MainGame {
    public static void main(String[] args) {
        GameEngine game = new GameEngine();
        game.playGame();
    }
}
```
