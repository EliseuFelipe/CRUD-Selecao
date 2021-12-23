import { http } from './config'

export default	{

  listar:()=>{
		return http.get('Autors')
  },

  create:(objeto) => {
    return http.post('Autors', objeto)
  },

  atualizar:(autor) => {
    return http.put('Autors/' + autor.id, autor)
  },

  deletar:(autor) => {
    return http.delete('Autors/' + autor.id, autor.id)
  },

  deletarLivro: (livro_id) => {
    return http.delete('livros/' + livro_id)
  }
}