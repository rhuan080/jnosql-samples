 			var appCatalogItem = new Vue({
	                el: '#app-catalog-item',
	                data: {
	                    catalogItems: [],
	                    mensagem : null,
	                    erro: false,
	                },
	                appNewCatalogItem:{},
	                mounted () {
	                   loadCatalogItem(this);
	                },
	                methods:{
		                loadNewCatalogItem: function(){

		                	if(this.appNewCatalogItem){
		                		return;
		                	}
		                	
		                	this.appNewCatalogItem =  new Vue({
				                el: '#app-new-catalog-item',
				                data: {
				                    mensagem : null,
				                    erro: false,
				                    form:{
				                        name:'',
				                        description:'',
				                        erro: false,
				                        mensagemErros:''
				                    }

				                },
				                methods: {
				                    save: function (e) {
				                        var config = {
				                            headers: {
				                            	'Content-Type': 'application/json',
				                            	'Origin': document.URL
				                        	}
				                        };

				                        var data = { 
				                        	         'name': this.form.name,
				                                     'description': this.form.description,
				                        }

				                        axios
				                            .post(this.$urlCatalogItem+"catalogitems" ,data, config )
				                            .then(response => {
				                                loadCatalogItem(appCatalogItem);
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
				                         	this.closeModal();


				                    },
				                    closeModal:function () {
				                    	$('#app-new-catalog-item').modal('hide');
				                    	this.cleanForm();
				                    },
				                    cleanForm: function (){
				                        this.form ={
					                        name:'',
					                        description:'',
					                        erro: false,
					                        mensagemErros:''
				                    	}
				                    }
				                },
				               

				            });
		                }
	                

	                }

	          });