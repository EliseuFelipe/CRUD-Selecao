<template>
    
  <div id="app" class="mainContainer">
    <div class="container">
            <h1>Sistema de Livros</h1>
            
            <hr>
                <form class="form-todo form-group" @submit.prevent>
                    <p>
                        <input placeholder="Nome do Autor" type="text" name="nome"
                        class="form-control" v-model="autor.nome"/>
                    </p>

                    <p>
                        <input placeholder="Email do autor" name="email"
                        class="form-control" v-model="autor.email"/>
                    </p>

                    <div id="div_livro" v-for="(input, index) in autor.livros">
                      <p>
                          <input placeholder="Nome do livro" name="livros.nome"
                          class="form-control" v-model="autor.livros[index].nome"/>
                      </p>

                      <p>
                          <input placeholder="Ano de lançamento" name="livros.data"
                          class="form-control" v-model="autor.livros[index].data"/>
                      </p>

                      <p>
                          <input placeholder="Quantidade de páginas" name="livros.paginas"
                          class="form-control" v-model="autor.livros[index].paginas"/>
                      </p>
                    
                      <p>
                        <button type="submit" class="btn btn-primary" @click="addField(input, autor.livros)">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-book" viewBox="0 0 16 16">
                              <path d="M1 2.828c.885-.37 2.154-.769 3.388-.893 1.33-.134 2.458.063 3.112.752v9.746c-.935-.53-2.12-.603-3.213-.493-1.18.12-2.37.461-3.287.811V2.828zm7.5-.141c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492V2.687zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783z"/>
                            </svg>
                            Add novo livro
                        </button>
                      </p>
                  
                    </div>

                    <button  type="submit" class="btn btn-success" @click="salvar()">
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
                            <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                        </svg>Salvar
                    </button>
                    
                </form>
                
                
            <hr/>

      <table>
        <tr class="list-group-item" v-for="autor in autores">
          <strong>Nome do Autor</strong>
          <td>{{ autor.nome }}</td>
        
          <strong>Email do autor</strong>
          <td>{{ autor.email }}</td>

          <strong>Livros do autor</strong>

          <th v-for="livro in autor.livros">
              <td>Nome: {{ livro.nome }}</td>
              <td>Data: {{ livro.data }}</td>
              <td>Páginas: {{ livro.paginas }}</td>
              <td>
                <button @click="deletarLivro(livro.id)" type="button" class="btn btn-danger">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                    <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                </svg>
                </button>
              </td>
          </th>
          

          <button @click="editar(autor)" class="btn btn-secondary">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
            </svg>Editar
          </button>

          <button @click="deletar(autor)" type="button" class="btn btn-danger">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
            </svg>Deletar
          </button>

    
        </tr>
      </table>

      </div>
  </div>
</template>

<script>

import dataService from './services/dataService'

export default {

  data(){
    return{
      autor: {
        id: '',
        nome: '',
        email: '',
        livros: [{
          nome: '',
          data: '',
          paginas: ''}
        ]
      },

      autores: [],

      aux: 1
    }
  },
  mounted(){
    this.listar()
  },
  methods:{
    listar(){
      
      dataService.listar().then(resposta => {
        resposta.data.map((objeto) => {
          console.log(objeto.livros.length);
          this.autores.push({
              id: objeto.id,
              nome: objeto.nome,
              email: objeto.email,
              livros: objeto.livros
          });
        })
      });
    },

    salvar(){
      console.log(JSON.stringify(this.autor));
      if(!this.autor.id){
        dataService.create(this.autor).then(resposta => {
          this.autor = {}
          location.reload();
        })
      }
      else{
        console.log("atualizar: " + this.autor.id);
        dataService.atualizar(this.autor).then(resposta => {
          this.autor = {}
          location.reload();
        })
      }
      
    },

    deletarLivro(livro_id){
      dataService.deletarLivro(livro_id).then(resposta => {
        location.reload();
      })
    },

    editar(autor){
      this.autor = autor
    },

    deletar(autor){
      dataService.deletar(autor).then(resposta => {
        location.reload();
      })
    },
    
    addField(value, fieldType){
      fieldType.push({nome: "", data: "", paginas: ''});
    }
  }

}
</script>
<style>


</style>
