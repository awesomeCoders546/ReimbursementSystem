import './App.css';
import  Header  from "./Header";
//  import  Menu  from "./Menu";
 import  Menu1  from "./Menu1";
 import  Add  from "./Add";
 import  Edit  from "./Edit";
function App(){
	return(
		<div class="wrapper">
			<Header/>
			<Menu1/>
			<Add/>
			<Edit/>
			{/* <Menu/> */}
			{/* <Footer/> */}
		</div>
	)
}


export default App;




