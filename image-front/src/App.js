import React, { useState } from 'react';
import axios from 'axios';

function App() {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [files, setFiles] = useState([]);
    const [selectedImg, setSelectedImg] = useState(null);

    const handleImageSelect = (e) => {
      const imagesArray = [];
      for(let i = 0; i< files.length; i++){
        const file = files[i];
        const reader = new FileReader();

        reader.onloadend = () => {
          imagesArray.push(reader.result);
          if(files.length === imagesArray.length){
            setSelectedImg(imagesArray);
          }
        };
        reader.readAsDataURL(file);
      }
    }
    return (
        <div className="App">
            <form>
                <div>
                    <label>제목:</label>
                    <input value={title} onChange={(e) => setTitle(e.target.value)}/>
                </div>
                <div>
                    <label>내용:</label>
                    <textarea  value={content} onChange={(e) => setContent(e.target.value)}/>
                </div>
                <hr></hr>
                <div>
                    <label htmlFor="selectImage">이미지선택</label>
                    <input type="file" id="selectImage" onChange={handleImageSelect}
                    style={{display: "none"}} accept='image/*' multiple/>
                </div>
                {selectedImg && (
                  <div>
                    <h3>미리보기</h3>
                    {selectedImg.map(img => (
                      <img src={img}/>
                    ))}
                  </div>
                )}
                <hr></hr>
                <button>Submit</button>
            </form>
        </div>
    );
}

export default App;