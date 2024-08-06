import React, { useEffect, useState } from 'react';
import axios from 'axios';

function App() {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [files, setFiles] = useState([]);
    const [posts, setPosts] = useState([]);

    const imageUploadToJava = () => {
      // Form : 특정 값을 가져와서 넘겨줄 때 사용하는 객체    
      const formData = new FormData();

      // files에서 파일이 하나가 아니라 여러개이기 때문에 여러개를 담을 배열 설정
      Array.from(files).forEach(file => {

        //foreach는 중괄호여도 return 생략 가능
        formData.append("files", file);
      });
      formData.append("title", title);
      formData.append("content", content);

      //자바 컨트롤러에 데이터 전송
      axios.post("/gallery/upload", formData, {
        headers:{
          "Content-Type" : "multipart/form-data"
        }
      });
      alert("자바로 이미지 보내기 성공");
    }
    useEffect(() => {
      fetch("/posts")
      .then(res => {
        return res.json();
      })
      .then(data => {
        console.log(data);
        setPosts(data);
      })
    },[files]);

    return (
        <div className="App">
          <div>
              <label>제목:</label>
              <input value={title} onChange={(e) => setTitle(e.target.value)}/>
          </div>
          <div>
              <label>내용:</label>
              <textarea value={content} onChange={(e) => setContent(e.target.value)}/>
          </div>
          <hr></hr>
          <div>
              <label htmlFor="selectImage">이미지선택</label>
              <input type="file" id="selectImage" onChange={(e) => setFiles(e.target.files)}
               multiple/>
          </div>
          <hr></hr>
          <button onClick={imageUploadToJava}>Submit</button>
          <hr></hr>
          <div>
          {posts.map(post => (
          <div key={post.id}>
            <h2>{post.title}</h2>
            <p>{post.content}</p>
            {post.imageUrl ? post.imageUrl.split(',').map((image, index) => (
              <img key={index} src={`http://localhost:9015/gallery/upload/${image}`}/>
            )) : <p>이미지가 없습니다.</p>}
          </div>
          ))}
      </div>
        </div>
    );
}

export default App;