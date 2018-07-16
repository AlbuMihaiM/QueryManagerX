========================================
	Query Manager - Mod de folosire
========================================

Pentru a putea folosi aplicatia, este necesara deschiderea unui
fisier in formatul .json. Acest lucru se face prin selectarea
File -> Open, apoi se alege din device fisierul dorit.
Daca fisierul nu are extensia .json, el nu se poate deschide.

Odata cu deschiderea sa, avem incarcate in program toate cererile
SQL (cu toate campurile sale) si le putem gestiona.

Se introduce ID-ul cererii dorite in campul "ID" si se da "Enter".
La apasarea Enter se va afisa cererea SQL corespondenta ID-ului
respectiv.

Daca ID-ul nu are un format permis (nu este un nr intreg /
nu apartine intervalului permis - de la 1 la numarul de cereri din
fisierul .json ales) se va produce o eroare.

In cazul tastarii unui numar intreg gresit, programul va redirectiona
utilizatorul catre cel mai apropiat numar intreg valid.

La completarea/modificarea campurilor "Adnotation 1" si "Adnotation 2"
se vor adauga/modifica acele campuri deja existente de adnotare
(doar la accesarea File->Save) ale cererii cu ID-ul completat in field,
in fisierul .json.

Un mic neajuns:
Deoarece numarul de cereri este foarte mare (24594 pentru fisierul
"train.json" pentru care am testat programul), folosind un editor mai slab
de texte precum "gedit", la reincarcarea fisierului deja deschis
programul crapa. Solutii:
- inchiderea fisierului inaintea suprascrierii si apoi deschiderea sa;
- folosirea unui editor de texte mai puternic ("Sublime"), realizeaza
	suprascrierea in aprox 3 sec.