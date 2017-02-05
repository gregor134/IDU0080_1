$(document).ready(function() {
    get_computers();

    $("#getAllCars").on('click', function() {
        get_computers();
    });

    $("#newComputer").submit(function(e) {
        var data = {
            'make': $('input[name="newMake"]').val(),
            'model': $('input[name="newModel"]').val(),
            'price': $('input[name="newPrice"]').val(),
            'processor': $('input[name="newProcessor"]').val()
        };
        console.log(JSON.stringify(data));
        $.ajax({
            url: '/rest/computers',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            dataType: 'json',
            success: function() {
                get_computers();
            }
        });
    });
});

var computer_from_server;

function get_computers() {
    $.ajax({
        url: 'rest/computers',
        type: "GET",
        dataType: 'json',
        success: function (data) {
            display_computers(data);
            console.log(JSON.stringify(data));
        }
    });
}

function get_computer(id) {
	$.ajax({
		url: 'rest/computer/' + id ,
		type: "GET",
		dataType: 'json',
		success: function(data) {
			computer_from_server = data;
			console.log(JSON.stringify(data));
			$("tr[id*='_change']").each(function() {
				$(this).remove();
			});
			var input_row = "<tr id='computer_" + id + "_change'>" +
                "<td></td>" +
                "<td><input type=text name=make class='form-control' value='" + data.make + "'/></td>" +
                "<td><input type=text name=model class='form-control' value='" + data.model + "'/></td>" +
                "<td><input type=text name=price class='form-control' value='" + data.price + "'/></td>" +
                "<td><input type=text name=processor class='form-control' value='" + data.processor + "'/></td>" +
                "<td><input class='btn btn-success' value='Salvesta' type='button' onClick='javascript:save_computer()'/></td>" +
                "</tr>";
            $("#computer_" + id).after($(input_row));
		}
	});
}

function save_computer() {
	computer_from_server.make=document.forms[0].make.value;
	computer_from_server.model=document.forms[0].model.value;
	computer_from_server.price=document.forms[0].price.value;
	computer_from_server.processor=document.forms[0].processor.value;

	var jsonData = JSON.stringify(computer_from_server);
	$.ajax({
		url: 'rest/computer/' + computer_from_server.id ,
		type: "PUT",
		data: jsonData,
		contentType: 'application/json',
		success: function(data) {
            get_computers();
		}
  	});
}

function display_computers(data) {
	var out_data="";
	out_data = out_data + "<table class='table' id='computersTable'><tr><td colspan=6>Arvuteid kokku: <b>" + data.length + "</b></td></tr>";
    out_data = out_data + "<tr><td><b>ID</b></td><td><b>Tootja</b></td><td><b>Mudel</b></td><td><b>Hind</b></td><td><b>Protsessor</b></td></tr>";
	for(var  i in data) {
		var computer = data[i];
        out_data = out_data + "<tr id='computer_" + computer.id + "'>" +
			"<td>" + computer.id +"</td>" +
            "<td class='make'>" + computer.make + "</td>" +
            "<td class='model'>" + computer.model + "</td>" +
            "<td class='price'>" + computer.price + "</td>" +
            "<td class='processor'>" + computer.processor + "</td>";
        out_data = out_data + "<td><input class='btn btn-danger pull-right' value='Kustuta' type='button' onClick='javascript:delete_computer(" + computer.id + ")'/>";
        out_data = out_data + "<input style='margin-right: 5px;' class='btn btn-default pull-right' value='Muuda' type='button' onClick='javascript:get_computer(" + computer.id + ")'/></td>";
    }
	out_data = out_data + "</table>";
	$("#computers_table").html(out_data);
}

function delete_computer(id) {
    $.ajax({
        url: 'rest/computer/' + id ,
        type: "DELETE",
        dataType: 'json',
        success: function(data) {
            computer_from_server = data;
            //display_computer(data);
            console.log(JSON.stringify(data));
            $("#computer_" + id).remove();
        }
    });
}



function show_message(message) {
	$("#msg_text").html(message);
}