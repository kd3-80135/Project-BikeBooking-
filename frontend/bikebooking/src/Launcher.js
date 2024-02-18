import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Footer from './Footer.js';
import Header from './Header.js';
import SignUp from './SignUp';
import Home from './Home.js';
import {Route,Link, Switch} from 'react-router-dom';
import SignIn from './SignIn.js';
import Admin from './Admin.js';
import Dealer from './Dealer.js';
import SetRole from './SetRole.js';
import EditProfile from './EditProfile.js';
import EditAddress from './EditAddress.js';
import Userlist from './UserList.js';
import BikeList from './BikeList.js';
import PartList from './PartList.js';
import DealerBikeList from './DealerBikeList.js';
import DealerPartList from './DealerPartList.js';
import AddBike from './AddBike.js';
import EditDealerBike from './EditDealerBike.js';
import AddPart from './AddPart.js';
import EditDealerPart from './EditDealerPart.js';
import CustomerBikeList from './CustomerBikeList.js';
import BikeDetails from './BikeDetails.js';
import CustomerPartList from './CustomerPartList.js';
import User from './User.js';
import PartDetails from './PartDetails.js';
import Cart from './Cart.js';

function Launcher(){
    return (
        <div className='container'>
            <Header />
            
            <hr></hr>
            <Switch>
                <Route exact path="/signup" component={SignUp}/>
                <Route exact path="/" component={Home}/>
                <Route path="/admin" exact component={Admin} />
                <Route path="/dealer" exact component={Dealer} />   
                <Route path="/user" exact component={User} />
                <Route exact path="/signin" component={SignIn}/>
                <Route exact path="/setrole" component={SetRole}/>
                <Route exact path="/edit" component={EditProfile}/>
                <Route exact path="/editAddress" component={EditAddress}/>
                <Route exact path="/userlist" component={Userlist}/>
                <Route exact path="/bikes" component={BikeList}/>
                <Route exact path="/parts" component={PartList}/>
                <Route exact path="/bikeList" component={DealerBikeList}/>
                <Route exact path="/partList" component={DealerPartList}/>
                <Route exact path="/addbike" component={AddBike} />
                <Route exact path="/editbike" component={EditDealerBike} />
                <Route exact path="/addPart" component={AddPart} />
                <Route exact path="/editpart" component={EditDealerPart} />
                <Route exact path="/customerbikelist" component={CustomerBikeList} />
                <Route path="/bikeDetails" component={BikeDetails} />
                <Route exact path="/partDetails" component={PartDetails} />
                <Route exact path="/customerpartlist" component={CustomerPartList} />
                <Route exact path="/cart" component={Cart} />

            </Switch>
            <Footer />
        </div>
    );
}

export default Launcher;