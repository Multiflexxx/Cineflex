var GewinneGewohnlich = ['Ein einzelnes Popcorn GRATIS', '3ml GRATIS zu deinem Getränk (ausgenommen Bier)', 'Logeneintritt GRATIS (Zum Aufpreis von 2€)', 'GUTSCHEIN: Kaufe 2 Tickets, und nimm zusätzlich zu dir noch eine weitere Person mit', 'KOSTENLOSER Eintritt ins Kino Foyer', '3x Toilettennutzung GRATIS', 'Berechtigung zum Kauf einer weiteren Lootbox', '1 Treuepunkt', '5€ Gutschein (zum Preis von 4,99€)', '30% Rabatt auf die Toiletten nutzung', 'Ein garantierter Sitzplatz beim Kauf einer Karte!'];
var GewinneSelten = ['3D Zuschlag Gratis', 'Gratis Keks', '5€ Gutschein beim Kauf eines Kinotickets', '100 Treuepunkte', 'Gratis Getränk beim Kaufen eines Menüs', 'Preview eines Films', 'Eine Portions Nachos umsonst (Salsa wird seperat verkauft)', 'Kostenloses Parken im Kino', 'Gehe über Start. Nimm dabei die 200M', 'Gratis Upgrade zum Logensitzplatz'];
var GewinneEpisch = ['1000 Treuepunkte', 'Privatvorstellung im Kino', 'Kino Backstage Tour', 'Treffen mit einem Filmstar', 'Ein kostenloser Kinoeintritt', 'Ein kostenloses Menü', 'Du bekommst für deinen Kinobesuch einen persönlichen Butler, der die während der Vorstellung die Füße massiert', 'Du hast verloren. Du darfst nie wieder unser Kino betreten.'];
var GewinneSuper = ['GOLDENE KARTE: Du darfst unlimitiert oft das Kino kostenlos besuchen', 'SILBERNE KARTE: Du darfst eine Woche lang so oft ins Kino, wie du willst.', 'BRONZENE KARTE: Du darfst 5 Mal ins Kino kostenlos']

function getRandomInt(max) {
    return Math.floor(Math.random() * Math.floor(max));
}

function openBoxG() {
    document.getElementById('gLoot').src = "img/specials/g_o.png";
    document.getElementById('gewinneg').innerHTML = "<ul><li>" + GewinneGewohnlich[getRandomInt(GewinneGewohnlich.length - 1)] + "</li><li>" + GewinneGewohnlich[getRandomInt(GewinneGewohnlich.length - 1)] + "</li><li>" + GewinneGewohnlich[getRandomInt(GewinneGewohnlich.length - 1)] + "</li><li>" + GewinneGewohnlich[getRandomInt(GewinneGewohnlich.length - 1)] + "</li><li>" + GewinneSelten[getRandomInt(GewinneSelten.length - 1)];
}

function openBoxS() {
    document.getElementById('sLoot').src = "img/specials/s_o.png";
    document.getElementById('gewinnes').innerHTML = "<ul><li>" + GewinneGewohnlich[getRandomInt(GewinneGewohnlich.length - 1)] + "</li><li>" + GewinneSelten[getRandomInt(GewinneSelten.length - 1)] + "</li><li>" + GewinneSelten[getRandomInt(GewinneSelten.length - 1)] + "</li><li>" + GewinneSelten[getRandomInt(GewinneSelten.length - 1)] + "</li><li>" + GewinneEpisch[getRandomInt(GewinneEpisch.length - 1)];
}

function openBoxE() {
    document.getElementById('eLoot').src = "img/specials/e_o.png";
    document.getElementById('gewinnee').innerHTML = "<ul><li>" + GewinneSelten[getRandomInt(GewinneSelten.length - 1)] + "</li><li>" + GewinneEpisch[getRandomInt(GewinneEpisch.length - 1)] + "</li><li>" + GewinneEpisch[getRandomInt(GewinneEpisch.length - 1)] + "</li><li>" + GewinneEpisch[getRandomInt(GewinneEpisch.length - 1)] + "</li><li>" + GewinneSuper[getRandomInt(GewinneSuper.length - 1)];
}