import { Link } from 'react-router-dom/cjs/react-router-dom.min';
import './index.css'
import logo from '../logo.png'

const Sidebar = () => {
    return (
        <div className='nav-bar '>
            <Link className="logo" to="/">
                <img src={logo} alt="logo"/>
                {/* <img src={logo} alt="logo"/> */}
            </Link>


        </div>
      
        )
    }

    export default Sidebar;