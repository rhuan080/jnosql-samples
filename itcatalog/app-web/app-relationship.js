				var apprelationship = new Vue({
	                el: '#app-relationship',
	                data: {
	                    relationships: [],
	                    catalogItems:[],
	                    catalogItem:'',
	                    relationship:'',
	                    mensagem : null,
	                    erro: false,
	                },
	                appNewRelationship:{},
	               
	                mounted () {

	                   loadCatalogItem(this);
                       
                   
	                },
	                methods:{
	                	find: function(){
	                   		loadRelationships(this)
	               		},
		                loadNewRelationship: function(){

		                	if(this.appNewRelationship){
		                		return;
		                	}

		                	this.appNewRelationship =  new Vue({
				                el: '#app-new-relationship',
				                data: {
				                    catalogItems: [],
				                    mensagem : null,
				                    erro: false,
				                    form:{
				                        idCatalogItemOne:'',
				                        nameCatalogItemOne:'',
				                        idCatalogItemTwo:'',
				                        nameCatalogItemTwo:'',
				                        relationship:''
				                        
				                    }

				                },
				                methods: {
				                    save: function (e) {
				                        var config = {
				                            headers: {
				                            	'Content-Type': 'application/x-www-form-urlencoded',
				                            	'Origin': document.URL
				                        	}
				                        };

				                        var data ={  
				                        	    idCatalogItemOne:this.form.idCatalogItemOne,
				                        		nameCatalogItemOne:this.catalogItems.filter(cat=>cat.id == this.form.idCatalogItemOne)[0].name,
				                        		idCatalogItemTwo:this.form.idCatalogItemTwo,
				                        		nameCatalogItemTwo:this.catalogItems.filter(cat=>cat.id == this.form.idCatalogItemTwo)[0].name,
				                        		relationship:this.form.relationship
				                        }

				                       

				                        axios
				                            .post(this.$urlrelationship+"itrelationships" ,transformDataRequest(data), config )
				                            .then(response => {
				                                loadRelationships(apprelationship);
				                                this.closeModal();
				                            })
				                            .catch(error => {

				                                this.form.erro = true;
				                                if (error.response) {
				                                    this.form.mensagemErros = error.response.data.mensagens;
				                                }
				                                else {
				                                    this.form.mensagemErros = ["Service failed. Try again soon."];
				                                }
				                            });

				                        
				                         	


				                    },
				                    closeModal:function () {
				                    	$('#app-new-relationship').modal('hide');
				                    	this.cleanForm();
				                    },
				                    cleanForm: function (){
				                        this.form ={
					                        idCatalogItemOne:'',
					                        nameCatalogItemOne:'',
					                        idCatalogItemTwo:'',
					                        nameCatalogItemTwo:'',
					                        relationship:''
				                        
				                    	}
				                    }
				                },
				                mounted () {
				                   loadCatalogItem(this);
				                }

				            });
		                }
	                

	                }

	            });

	          

	          
