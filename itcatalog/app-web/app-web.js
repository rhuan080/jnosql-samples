	 Vue.prototype.$urlrelationship = 'http://172.18.0.5:8080/';
	 Vue.prototype.$urlCatalogItem = 'http://172.18.0.3:8080/';

	            

	            function loadRelationships(app){

	            	if(!app.catalogItem){
	            		return;
	            	}

	                axios
	                    .get(app.$urlrelationship + 'itrelationships/'+app.catalogItem + '?relationship=' +app.relationship)
	                    .then(response => {
	                        app.relationships = response.data;
	                    })
	                    .catch(error => {

	                        app.erro = true;
	                        if (error.response) {
	                            this.mensagem = error.response.data.mensagens;
	                        }
	                        else {
	                            this.mensagem = "Service failed. Try again soon.";
	                        }

	                    });
	            }

	            function loadCatalogItem(app){
	                axios
	                    .get(app.$urlCatalogItem + 'catalogitems')
	                    .then(response => {
	                        app.catalogItems = response.data;

	                        if(!app.catalogItem || !app.relationship){
		                         if(typeof app.catalogItems !== 'undefined' && app.catalogItems.length > 0){
		                       		app.catalogItem = app.catalogItems[0].id;
		                       	 }

		                       	 app.relationship = 'SUPPORTS';
							}

	                        if(app.$el.id == 'app-relationship'){

		                       loadRelationships(app);
		                   	}
	                    })
	                    .catch(error => {

	                        app.erro = true;
	                        if (error.response) {
	                            this.mensagem = error.response.data.mensagens;
	                        }
	                        else {
	                            this.mensagem = "Service failed. Try again soon.";
	                        }

	                    });
	            }

	            function transformDataRequest(jsonData){

	            	return Object.entries(jsonData)
    							.map(x => `${encodeURIComponent(x[0])}=${encodeURIComponent(x[1])}`)
    							.join('&');
	            }

	           

	          
