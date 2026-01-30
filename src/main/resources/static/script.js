async function renderMap(){
    const lat=document.getElementById('lat').value;
    const lon=document.getElementById('lon').value;
    const zoom=document.getElementById('zoom').value;

    const response =await fetch(`/convert?lat=${lat}&lon=${lon}&zoom=${zoom}`);
    const data =await response.json();

    const canvas= document.getElementById('mapCanvas');
    const ctx=canvas.getContext('2d');

    const tileUrl='https://tile.openstreetmap.org/${data.zoom}/${data.tileX}/${data.tileY}.png';

    const img=new Image();
    img.crossOrigin="Anonymous";
    img.src=tileUrl;

    img.onload=function (){
        ctx.clearRect(0,0,canvas.width,canvas.height);
        ctx.drawImage(img,128,128);
        const offsetX=data.pixelX-(data.tileX*256);
        const offsetY=data.pixelY-(data.tileY*256);

        ctx.beginPath();
        ctx.arc(128+offsetX,128+offsetY,8,0,2*Math.PI);
        ctx.fillStyle="red";
        ctx.fill();
        ctx.strokeStyle="white";
        ctx.lineWidth=3;
        ctx.stroke();

        console.log("Point drawn at tile offset:",offsetX,offsetY);
    };
}