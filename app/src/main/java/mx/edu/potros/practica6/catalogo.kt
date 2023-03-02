package mx.edu.potros.practica6

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text


class catalogo : AppCompatActivity() {
    var adapter: PeliculaAdapter? =null
    var adapterSeries: PeliculaAdapter? =null
    var peliculas=ArrayList<Pelicula>()
    var series=ArrayList<Pelicula>()
    var gridViewMovies: GridView?=null
    var gridViewSeries: GridView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo)
        cargarPeliculas()
        adapter=PeliculaAdapter(peliculas,this)
        adapterSeries=PeliculaAdapter(series,this)
        gridViewMovies=findViewById(R.id.gridViewMovies)
        gridViewSeries=findViewById(R.id.gridViewSeries)
        gridViewMovies?.adapter=adapter
        gridViewSeries?.adapter=adapterSeries
    }

    fun cargarPeliculas(){

        peliculas.add(Pelicula("1917",R.drawable.war,R.drawable.warheader,"British trenches somewhere in france.World war has been going"))
        peliculas.add(Pelicula("Big Hero 6",R.drawable.bighero6,R.drawable.headerbighero6,"Cuando un giro inesperado de eventos los sumerge en el medio de un peligroso plan, un niño prodigio, su robot y sus amigos se convierten en héroes de alta tecnología en una misión para salvar su ciudad."))
        peliculas.add(Pelicula("Inception",R.drawable.inception,R.drawable.inceptionheader,"Dom Cobb (Leonardo DiCaprio) es un ladrón con una extraña habilidad para entrar a los sueños de la gente y robarle los secretos de sus subconscientes. Su habilidad lo ha convertido en un atractivo en el mundo del espionaje corporativo, pero ha tenido un gran costo en la gente que ama. Cobb tiene la oportunidad de ser perdonado cuando recibe una tarea imposible: plantar una idea en la mente de una persona. Si tiene éxito, será el crimen perfecto, pero un enemigo se anticipa a sus movimientos."))
        peliculas.add(Pelicula("Leap Year",R.drawable.leapyear,R.drawable.leapyearheader,"Una joven viaja a Dublín para, aprovechando una costumbre irlandesa, pedirle a su novio que se case con ella, sabiendo que no puede decir que no en ese día tan especial, el 29 de febrero. Sin embargo las cosas no saldrán como había planeado. Ella termina varada en el otro lado de la Isla Esmeralda con un guapo, pero hosco hombre gaélico."))
        peliculas.add(Pelicula("Men in Black",R.drawable.mib,R.drawable.mibheader,"Durante muchos años los extraterrestres han vivido en la Tierra, mezclados con los seres humanos, sin que nadie lo supiese. Los Hombres de Negro son agentes especiales que forman parte de una unidad altamente secreta del gobierno; su misión consiste en controlar a los alienígenas. Dos de estos agentes (uno veterano y otro recién incorporado), cuya misión consiste en vigilar a los alienígenas que viven en Nueva York, descubren a un terrorista galáctico que pretende acabar con la Humanidad."))
        peliculas.add(Pelicula("Toy Story",R.drawable.toystory,R.drawable.toystoryheader,"Los juguetes de Andy, un niño de seis años, temen que un nuevo regalo les sustituya en el corazón de su dueño. Woody, un vaquero que ha sido hasta ahora el juguete favorito, trata de tranquilizarlos hasta que aparece Buzz Lightyear. Lo peor es que el arrogante Buzz se cree que es una auténtico astronauta en plena misión para regresar a su planeta."))

        series.add(Pelicula("Dr.House",R.drawable.drhouse,R.drawable.drhouse,"La serie gira alrededor del Dr. Gregory House, un misántropo genio de la medicina, ególatra y de fuerte y marcada personalidad que dirige el departamento de diagnóstico médico del ficticio Hospital Universitario Princeton-Plainsboro de Nueva Jersey, dirigido por la Dra. Lisa Cuddy."))
        series.add(Pelicula("Dr.Who",R.drawable.drwho,R.drawable.drwhoheader,"La serie narra las aventuras de un Señor del Tiempo conocido como «el Doctor», que explora el universo en su TARDIS, una nave espacial con conciencia propia capaz de viajar a través del tiempo y el espacio."))
        series.add(Pelicula("Friends",R.drawable.friends,R.drawable.friendsheader,"Monica, Rachel, Phoebe, Chandler, Ross y Joey son seis amigos treintañeros que viven en Nueva York. Juntos afrontan con humor las dificultades propias de su edad: líos amorosos, trabajo, familia y su propia convivencia."))
        series.add(Pelicula("Smallville",R.drawable.smallville,R.drawable.smallvilleheader," La serie narra las aventuras del joven Clark Kent en el pueblo ficticio de Smallville, Kansas, durante los años previos a que él se convierta en Superman. Las primeras cuatro temporadas se centran en Clark y sus amigos durante el instituto (High School)."))
    }

    class PeliculaAdapter : BaseAdapter{
        var peliculas =ArrayList<Pelicula>()
        var contexto: Context? =null

        constructor(peliculas:ArrayList<Pelicula>,context: Context):super(){
            this.peliculas=peliculas
            this.contexto=context
        }

        override fun getCount(): Int {
            return peliculas.size
        }

        override fun getItem(p0: Int): Any {
            return peliculas[p0]
        }

        override fun getItemId(p0: Int): Long {
           return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var pelicula =peliculas[p0]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.peliculas,null)
            var imagen= vista.findViewById(R.id.iv_pelicula) as ImageView
            var nombre= vista.findViewById(R.id.iv_titulo) as TextView

            imagen.setImageResource(pelicula.image)
            nombre.setText(pelicula.titulo)

            vista.setOnClickListener{
                var intent = Intent(contexto,detalle_pelicula::class.java)
                intent.putExtra("nombre",pelicula.titulo)
                intent.putExtra("image",pelicula.image)
                intent.putExtra("header",pelicula.header)
                intent.putExtra("sinopsis",pelicula.sinopsis)
                contexto!!.startActivity(intent)
            }
            return vista
        }


    }
}