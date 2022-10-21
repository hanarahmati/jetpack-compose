package co.fanavari.mycomposeapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import co.fanavari.mycomposeapplication.ui.theme.MyComposeApplicationTheme
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyComposeApplicationTheme {
                MyApp()
                FirstPartPreview()
            }
        }
    }

    @Composable
    fun MyApp() {
        var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }

        if (shouldShowOnBoarding) {
            OnBoardingScreen(onContinueClicked = { shouldShowOnBoarding = false })
        } else {
            Greetings()
        }
    }

    @Composable
    fun OnBoardingScreen(onContinueClicked: () -> Unit) {

        Surface {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Welcome to Basics Codelab!")
                Button(
                    modifier = Modifier.padding(vertical = 24.dp),
                    onClick = onContinueClicked
                ) {
                    Text(text = "Continue")

                }
            }
        }
    }

    @Composable
    private fun Greetings(names: List<String> = List(1000) { "$it" }) {
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            items(items = names) { name ->
                Greeting(name = name)
            }
        }
    }

     /*@Preview(showBackground = true, widthDp = 320, heightDp = 320)
     @Composable
     fun OnBoardingPreview() {
         MyComposeApplicationTheme {
             OnBoardingScreen(onContinueClicked = {})

         }
     }*/

    @Composable
    private fun Greeting(name: String) {

        val expanded = rememberSaveable { mutableStateOf(false) }

        val extraPadding by animateDpAsState(
            if (expanded.value) 48.dp else 0.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        Surface(
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Row(modifier = Modifier.padding(24.dp)) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = extraPadding.coerceAtLeast(0.dp))
                ) {
                    Text(text = "Hello, ")
                    Text(text = name)
                }
                OutlinedButton(
                    onClick = { expanded.value = !expanded.value }
                ) {
                    Text(if (expanded.value) "Show less" else "Show more")
                }
            }
        }
    }

      /*@Preview(showBackground = true, widthDp = 320)
      @Composable
      fun DefaultPreview() {
          MyComposeApplicationTheme {
              Greetings()
          }
      }*/


    @Preview(showBackground = true, widthDp = 320, heightDp = 520)
    @Composable
    private fun FirstPartPreview() {
        MyComposeApplicationTheme {
            //FirstPart()
            //SecondPart()
            //ThirdPart()
            //ForthPart()
            //FifthPart()
            //SixthPart()
            SeventhPart()
        }
    }

    @Composable
    fun FirstPart() {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxSize()
                .padding(5.dp)
                .border(5.dp, Color.Cyan)
                .padding(5.dp)
                .border(5.dp, Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = "Hello", modifier = Modifier
                .border(5.dp, Color.Red)
                .padding(5.dp)
                .offset(10.dp)
                .clickable {

                })
            Spacer(modifier = Modifier.height(100.dp))
            Text("World")
            Text("Compose")
            Row(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(0.5f)
                    .width(200.dp)
                    .height(200.dp)
                    .requiredWidth(600.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("My")
                Text("Jetpack compose")
            }
        }

    }

    @Composable
    fun SecondPart() {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(16.dp)
        ) {
            ImageCard(
                painter = painterResource(id = R.drawable.fruit),
                contentDescription = "image card",
                title = "test"
            )
        }

    }
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(0.5f),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(modifier = modifier.height(200.dp)) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    title,
                    style = TextStyle(color = Color.White, fontSize = 16.sp)
                )
            }
        }

    }
}

@Composable
fun ThirdPart() {
    val fontFamily = FontFamily(
        Font(R.font.s_koodak),
        Font(R.font.s_koodakb)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {
        Text(
//                text = "JetPack Compose",
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 50.sp
                    )
                ) {
                    append("J")
                }
                append("etpack")
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 50.sp
                    )
                ) {
                    append("C")
                }
                append("ompose")
            },
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun ColorBox(
    modifier: Modifier = Modifier,
    updateColor: (Color) -> Unit
    ){

    Box(modifier = modifier
        .background(Color.Red)
        .clickable {
            updateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            )
        })
}
@Composable
fun ForthPart(){
    Column(Modifier.fillMaxSize()) {
        val color = remember {
            mutableStateOf(Color.Yellow)
        }
        ColorBox(
            Modifier
                .weight(1f)
                .fillMaxSize()
        ){
            color.value = it
        }
        Box(modifier = Modifier
            .background(color.value)
            .weight(0.5f)
            .fillMaxSize())
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FifthPart() {
    val scaffoldState = rememberScaffoldState()
    var textFieldState by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    val text = stringResource(id = R.string.app_name)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            TextField(value = textFieldState,
                label = {
                    "Enter your name"
                }, onValueChange = {
                    textFieldState = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("$text $textFieldState")
                }
            }) {
                Text("pls greet me")
            }
        }

    }
}

@Composable
fun SixthPart(){

    val scrollState = rememberScrollState()
    Column {
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .fillMaxHeight(0.3f)
        ) {
            for(i in 1..50){
                Text(
                    text = "Item $i",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .border(5.dp, Color.Red)
                        .fillMaxHeight()
                        .padding(horizontal = 24.dp)

                )
            }
        }
        LazyRow(modifier = Modifier
            .fillMaxHeight(0.3f)){
            itemsIndexed(
                listOf("android", "kotlin", "java", "compose", "xml")
            ){ index, string ->
                Text(
                    text = "$string",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .border(5.dp, Color.Red)
                        .fillMaxHeight()
                        .padding(horizontal = 24.dp)
                )
            }
        }
        LazyRow(
            modifier = Modifier
                .border(10.dp, Color.Cyan)
                .fillMaxHeight(0.4f)
        ){
        items(5000){
            Text(
                text = "Item $it",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .border(5.dp, Color.Red)
                    .padding(horizontal = 24.dp)
            )
        }
    }
    }




}

@Composable
fun SeventhPart(){
    val constraints = ConstraintSet {
        val greenBox = createRefFor("greenBox")
        val redBox = createRefFor("redBox")
        val guidLine = createGuidelineFromTop(0.5f)

        constrain(greenBox){
            top.linkTo(parent.top)
//            top.linkTo(guidLine)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
        constrain(redBox){
            top.linkTo(parent.top)
            start.linkTo(greenBox.end)
            end.linkTo(parent.end)
           // width = Dimension.value(100.dp)
            width = Dimension.fillToConstraints
            height = Dimension.value(100.dp)
        }
        createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Spread)
    }
    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .background(Color.Green)
            .layoutId("greenBox"))
        Box(modifier = Modifier
            .background(Color.Red)
            .layoutId("redBox"))
    }
}