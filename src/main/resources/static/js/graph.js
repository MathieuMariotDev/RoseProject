// Load the Visualization API and the corechart package.
google.charts.load('current', {'packages':['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawChart);

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


//var nbContactsByEntreprise = parseInt($("#nbContactsByEntreprise").val())

function drawChart2() {

    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Entreprise');
    data.addColumn('number', 'Number');
    data.addRows([
        ['Prospects', nbProspects],
        ['Clients', nbClients]
    ]);

    // Set chart options
    var options = {
        'title': 'Répartition du nombre de contacts',
        'width': 400,
        'height': 300
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}
