// Load the Visualization API and the corechart package.
google.charts.load('current', {'packages':['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawChart);
google.charts.setOnLoadCallback(drawChart2);
google.charts.setOnLoadCallback(drawChart3);

var nbProspects = parseInt($("#nbProspects").val());
var nbClients = parseInt($("#nbClients").val());

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart() {

    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Contacts');
    data.addColumn('number', 'Number');
    data.addRows([
        ['Prospects', nbProspects],
        ['Clients', nbClients]
    ]);

    // Set chart options
    var options = {'title':'Répartition du nombre de contacts',
        'width':400,
        'height':300};

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}


var entreprisesbyNotesCount = JSON.parse($("#entreprisesbyNotesCount").val());
//console.log(entreprisesbyNotesCount);

var entreprisesNames = Object.keys(entreprisesbyNotesCount);

function drawChart2() {

    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Entreprise');
    data.addColumn('number', 'Number');

    entreprisesNames.forEach(entreprise => {
        data.addRow([
            entreprise, entreprisesbyNotesCount[entreprise]
        ]);
    })

    // Set chart options
    var options = {
        'title': 'Répartition des notes par entreprise',
        'width': 400,
        'height': 300
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.PieChart(document.getElementById('chart_div_2'));
    chart.draw(data, options);
}


var nbProspectsProspectionAucun = parseInt($("#nbProspectsProspectionAucun").val());
var nbProspectsProspectionEnCours = parseInt($("#nbProspectsProspectionEnCours").val());
var nbProspectsProspectionARelancer = parseInt($("#nbProspectsProspectionARelancer").val());
var nbProspectsProspectionTermine = parseInt($("#nbProspectsProspectionTermine").val());

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart3() {

    var data = google.visualization.arrayToDataTable([
        ["Prospection", "Number", { role: "style" } ],
        ["Aucune", nbProspectsProspectionAucun, "#b87333"],
        ["En cours", nbProspectsProspectionEnCours, "silver"],
        ["A relancer", nbProspectsProspectionARelancer, "gold"],
        ["Terminé", nbProspectsProspectionTermine, "color: #e5e4e2"]
    ]);

    var view = new google.visualization.DataView(data);
    view.setColumns([0, 1,
        { calc: "stringify",
            sourceColumn: 1,
            type: "string",
            role: "annotation" },
        2]);


    // Set chart options
    var options = {'title':'Nombre de prospects par état de prospection',
        'width':600,
        'height':400,
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.ColumnChart(document.getElementById('columnchart_values'));
    chart.draw(view, options);
}
