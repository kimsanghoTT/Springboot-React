import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './App.css';

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
      headers: {
        "Content-Type": "multipart/form-data"
      }
    });
    alert("자바로 이미지 보내기 성공");
  }

  const getPosts = () => {
    axios.get("http://localhost:9015/posts") // 컨트롤러 url api 주소에서 데이터 가져오기
      .then(res => {
        setPosts(res.data);
        console.log(res.data);
      })
    //이미지 업로드를 db에 하고 나서 업로드 된 이미지를 불러오기
    //getPosts();
  }

  useEffect(() => {
    getPosts();
  }, []);

  return (
    <div className="App">
      <div className='form-container'>
        <table>
          <tbody>
            <tr>
              <td><label>제목:</label></td>
              <td><input value={title} onChange={(e) => setTitle(e.target.value)} /></td>
            </tr>
            <tr>
              <td><label>내용:</label></td>
              <td><textarea value={content} onChange={(e) => setContent(e.target.value)} /></td>
            </tr>
            <tr>
              <td>
                <label htmlFor="selectImage" className='file-label'>이미지선택
                  <input type="file" id="selectImage" style={{display: "none"}} onChange={(e) => setFiles(e.target.files)}
                  multiple />
                </label>
                
              </td>
              <td><button onClick={imageUploadToJava}>imageUpload</button></td>
            </tr>
          </tbody>
        </table>
        <div className='posts-container'>

        </div>
        {posts.map(post => (
          <div key={post.id} className='post'>
            <h2>{post.title}</h2>
            <p>{post.content}</p>
            {/* 
            {post.files && post.files.map(file => ()
              - post.files = 존재할 경우에만 뒤의 코드가 실행

            {post.files && post.files.map(file => (
              <img key={file} src={file} alt='imgFile'/>
            ))}
              - Array에 대한 배열이 제대로 나오지 않으면 에러가 발생함 -> , 구분을 따로 설정
              - DB에 이미지 여러장 저장할 때 ','로 구분해서 저장했으므로 , 기준으로 이미지 구문을 나눠야함
            */}
            <div className='images'>
            {post.imageUrl.split(',').map(image => (
              <img key={image} src={`http://localhost:9015/images/${image}`} alt='imgFile' />
            ))}
            </div>
            <p>{post.createdAt}</p>
            <button>이미지 수정하기</button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;