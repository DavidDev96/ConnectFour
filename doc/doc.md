# Dokumentation:

## System:


## ConnectFour-Game:


## Tests:


### Mock vs. Spy in Mockito:

Wenn Mockito ein Mock erstellt, geschieht dies anhand der Klasse eines Typs, nicht anhand einer tatsächlichen Instanz.
Der Mock erstellt einfach eine einfache Shell-Instanz der Klasse, die vollständig instrumentiert ist, um Interaktionen mit ihr zu verfolgen.

Der Spion hingegen umhüllt eine vorhandene Instanz. Sie verhält sich genauso wie die normale Instanz; der einzige Unterschied ist, dass sie auch instrumentiert wird, um alle Interaktionen mit ihr zu verfolgen.

![img_1.png](img_1.png)

Ein Stub ist ein kontrollierbarer Ersatz für eine bestehende Abhängigkeit (oder einen Kollaborateur) im System. Durch die Verwendung eines Stubs können Sie Ihren Code testen, ohne sich direkt mit der Abhängigkeit auseinandersetzen zu müssen.

Ein Mock-Objekt ist ein unechtes Objekt im System, das darüber entscheidet, ob der Unit-Test bestanden hat oder nicht. Dazu wird überprüft, ob das zu testende Objekt wie erwartet mit dem Scheinobjekt interagiert.

![img.png](img.png)

## CI/CD:

### Systemvoraussetzung

siehe "Systemvoraussetzung für die 3.Übung.pdf"

### GitHub Hosted Runner

GitHub bietet gehostete virtuelle Maschinen zur Ausführung von Workflows an. Die virtuelle Maschine enthält eine Umgebung mit Tools, Paketen und Einstellungen, die für GitHub-Aktionen zur Verfügung stehen.

Runner sind die Maschinen, die Aufgaben in einem GitHub-Aktions-Workflow ausführen. Ein Runner kann zum Beispiel Ihr Repository lokal klonen, Testsoftware installieren und dann Befehle ausführen, die Ihren Code bewerten.
## Aufgabenstellung:


![img_2.png](img_2.png)