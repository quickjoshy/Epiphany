import { useState } from 'react';
import './app.css'

const getUrl = "http://localHost:8080/RandomMessage";
  const getOptions = {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    }
  };

  const putUrl = "http://localHost:8080/Add"; // Concat /LikeOrDislike/id
  const putOptions = {
    method: 'POST'
  }


function App() {
  

  const [message, setMessage] = useState<any>({likes: 0, dislikes: 0, text: ""});
  const [hasLiked, setHasLiked] = useState(false);
  const [hasDisliked, setHasDisliked] = useState(false);
  function GetNewMessage() {
      fetch(getUrl, getOptions)
        .then(response => {
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        return response.json();
      })
      .then(data => {
        setMessage(data);
        console.log('Fetched data:', data);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
    }

    function IncrementLike(){

    }
  

  return (
    <div className='wrapperContainer'>
    <div className='msgDisplay'>{message.text}</div>

    <div className='wrapper'>
    
    <button onClick={() => {setHasLiked(true); IncrementLike();}} className='likeDisplay'>Likes: {message.likes}</button>
    <button className='dislikeDisplay'>Dislikes: {message.dislikes}</button>
    </div>
    <div></div>
    <button className='msgButton' onClick={() => {GetNewMessage();}}>Random Message</button>
    </div>
  )
}



export default App
