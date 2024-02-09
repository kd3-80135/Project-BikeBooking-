import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Footer from './Footer.js';
import Header from './Header.js';
import SignUp from './SignUp';
import Home from './Home.js';
import {Route,Link, Switch} from 'react-router-dom';
import SignIn from './SignIn.js';
import Admin from './Admin.js';
import Dealer from './Dealer.js';
import User from './User.js';
import SetRole from './SetRole.js';
import EditProfile from './EditProfile.js';
import EditAddress from './EditAddress.js';
import Userlist from './UserList.js';

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
            </Switch>
            <Footer />
        </div>
    );
}

export default Launcher;