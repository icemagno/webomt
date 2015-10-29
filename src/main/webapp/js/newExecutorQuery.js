var codeMirrorEditor = null;
var targetRelation = null;
var targetFields = [];
var temSourceRelation = null;
var tempSourceFields = [];
var sourceRelations = [];
var sourceFields = [];
var whatPanel = null;

function explainQuery( sqlQuery ) {
	$.ajax({
		type: "GET",
		url: "explainQuery",
		data: { query: sqlQuery }
	}).done(function( content ) {
		$("#explainContent").html( content );
	});
}

function addSourceField( fieldName ) {
	var found = false;
	$.each( tempSourceFields , function( index, value ) {
		if ( fieldName == value ) {
			found = true;
		}
	});
	if ( !found ) {
		tempSourceFields.push( fieldName );
	}
}


function addTargetField( fieldName ) {
	var found = false;
	$.each( targetFields , function( index, value ) {
		if ( fieldName == value ) {
			found = true;
		}
	});
	if ( !found ) {
		targetFields.push( fieldName );
	}
}

function storeSourceRelation( tableName, fields ) {

	if ( relationExists( tableName ) ) {
		$.each( sourceRelations , function( index, value ) {
			var relation = sourceRelations[ index ];
			if( relation["relationName"] == tableName ) {
				$.each( fields , function( indexField, valueField ) {
					relation["fields"].push( valueField );
				});
			}
		});
	} else {
		sourceRelations.push( {"relationName":tableName, "fields" : fields} );
	}
}

function relationExists( relationName ) {
	var retorno = false;
	$.each( sourceRelations , function( index, value ) {
		var relation = sourceRelations[ index ];
		var rName = relation["relationName"];
		if( relationName == rName ) {
			retorno = true;
		}
	});
	return retorno;
}


function generateSql() {

	//====================================================================
	var sql = "insert into " + targetRelation + " (id_instance, id_experiment, id_activity, ";
	$.each( targetFields , function( index, value ) {
		sql = sql + value + ", ";
	});
	sql = sql.substring(0, sql.length - 2) + ") select %ID_PIP%, %ID_EXP%, %ID_ACT%, ";

	
	//====================================================================
	var selectFields = "";
	var fromTables = "";
	if ( sourceRelations.length > 0 ) {
		
		$.each( sourceRelations , function( index, value ) {
			var relation = sourceRelations[ index ];
			var rName =  relation["relationName"];
			var fields = relation["fields"];
			
			$.each( fields , function( indexField, valueField ) {
				selectFields = selectFields + rName + "." + valueField + ", ";
			});
			
			if (fromTables.indexOf( rName ) == -1) {
				fromTables = fromTables + rName + ", ";
			}
			
		});
		
	}
	
	//====================================================================
	if ( tempSourceFields.length > 0 ) {
		$.each( tempSourceFields , function( index, value ) {
			selectFields = selectFields + tempSourceRelation + "." + value + ", ";
		});
		
		if (fromTables.indexOf( tempSourceRelation ) == -1) {
			fromTables = fromTables + tempSourceRelation + ", ";
		}
	}

	//====================================================================
	selectFields = selectFields.substring(0, selectFields.length - 2);
	fromTables = fromTables.substring(0, fromTables.length - 2);
	
	//====================================================================
	sql = sql + selectFields + " from " + fromTables;
	codeMirrorEditor.getDoc().setValue( sql );
	showSourceTables();
	
} 

function removeField( fieldName ) {
	var table = fieldName.split(".")[0];
	var field = fieldName.split(".")[1];
	var newTables = [];
	
	$.each( sourceRelations , function( index, value ) {
		var relation = sourceRelations[ index ];
		var relationName = relation["relationName"];
		var fields = relation["fields"]; 
		var newFields = [];
		
		$.each( fields , function( indexField, valueField ) {
			if ( field != valueField ) {
				newFields.push( valueField );
			}
		});
		if ( newFields.length > 0 ) {
			newTables.push( {"relationName":relationName, "fields" : newFields} );
		}
		
	});
	sourceRelations = newTables;
	generateSql();
}

function showSourceTables() {
	$("#srcTbl").find("tr").remove();
	$.each( sourceRelations , function( index, value ) {
		var relation = sourceRelations[ index ];
		var rName =  relation["relationName"];
		var fields = relation["fields"];
		var link = "";
		var attribue = "";
		var trRow = "<tr><td style='padding: 1px;'>" + rName + "</td><td style='padding: 1px;'>";
		var fieldsInRow = "";
		$.each( fields , function( indexField, valueField ) {
			attribute = rName + "." + valueField;
			link = "<a href='#' onclick='removeField(\""+attribute+"\");' >"+valueField+"</a>"; 
			fieldsInRow = fieldsInRow + link + "<br>";
		});
		trRow = trRow + fieldsInRow + "</td></tr>";
		$("#srcTbl").append( trRow );
	});
	
}
