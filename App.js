import './App.css';
import  Header  from "./Header";
//  import  Menu  from "./Menu";
 import  Menu1  from "./Menu1";
 import  Add  from "./Component/Add";
 import  Edit  from "./Component/Edit";
 import Fade from 'react-reveal/Fade';
import {ArrowDownOutlined} from '@ant-design/icons';
import Zoom from 'react-reveal/Zoom';
import Dislaytable from "./Component/Displaytable";
import Roll from 'react-reveal/Roll';


function App(){
	  
	return(
		<div class="wrapper">
			<Header/>
			 <Menu1/>
			<div className="wrap">
			
			<Fade top>
         		 <h1>Welcome to the Reimbursement</h1>
				  <ArrowDownOutlined />    
        	</Fade>
			</div>
			<div className="add">
			<Zoom left>
				<Add/>
       		</Zoom>
			</div>

			<div className="edit">
			<Zoom right>
			   <Edit/>
        	</Zoom>
			</div>
			<div className="display">
			<Roll left>
			<Dislaytable/>
			</Roll>
			</div>
			{/* /* <Menu/> */
			/* <Footer/> */ }
		</div>
	)
}


export default App;




