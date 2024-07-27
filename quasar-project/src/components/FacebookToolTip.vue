<template>
  <div class="tooltip-container">
    <div class="tooltip">
      <div class="profile">
        <div class="user">
          <div class="img">FB</div>
          <div class="details">
            <div class="name">User</div>
            <div class="username">@username</div>
          </div>
        </div>
        <div class="about">500+ Friends</div>
      </div>
    </div>
    <div class="text">
      <a class="icon" href="https://facebook.com/">
        <div class="layer">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          <span class="fab fa-facebook">
            <svg viewBox="0 0 448 512" height="1em">
              <path
                d="M448 56.7v398.5c0 31.3-25.4 56.7-56.7 56.7H56.7C25.4 512 0 486.6 0 455.3V56.7C0 25.4 25.4 0 56.7 0h334.6C422.6 0 448 25.4 448 56.7zM291.2 181.6h-29.2v-19.7c0-8.5 5.3-10.5 9-10.5h20.2V112h-27.6c-30.7 0-37.4 22.5-37.4 37.4v21.8h-16.6v30.4h16.6v93.5h38.4v-93.5h25.9l4.3-30.4z"
              ></path>
            </svg>
          </span>
        </div>
        <div class="text">Facebook</div>
      </a>
    </div>
  </div>
</template>

<style scoped>
.tooltip-container {
  position: relative;
  cursor: pointer;
  transition: all 0.2s;
  font-size: 17px;
  border-radius: 10px;
  display: inline-block;
}

.tooltip {
  position: absolute;
  top: 0;
  left: 50%;
  transform: translateX(-50%);
  padding: 10px;
  opacity: 0;
  pointer-events: none;
  transition: all 0.3s;
  border-radius: 15px;
  box-shadow: inset 5px 5px 5px rgba(0, 0, 0, 0.2),
    inset -5px -5px 15px rgba(255, 255, 255, 0.1),
    5px 5px 15px rgba(0, 0, 0, 0.3), -5px -5px 15px rgba(255, 255, 255, 0.1);
}

.profile {
  background: #3b5998;
  border-radius: 10px 15px;
  padding: 10px;
  border: 1px solid rgba(11, 63, 95, 1);
}

.tooltip-container:hover .tooltip {
  top: -150px;
  opacity: 1;
  visibility: visible;
  pointer-events: auto;
}

.icon {
  text-decoration: none;
  color: #fff;
  display: block;
  position: relative;
}
.layer {
  width: 55px;
  height: 55px;
  transition: transform 0.3s;
}
.icon:hover .layer {
  transform: rotate(-35deg) skew(20deg);
}
.layer span {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  border: 1px solid #fff;
  border-radius: 5px;
  transition: all 0.3s;
}

.layer span,
.text {
  color: #4267b2;
  border-color: #4267b2;
}

.icon:hover.layer span {
  box-shadow: -1px 1px 3px #4267b2;
}
.icon .text {
  position: absolute;
  left: 50%;
  bottom: -5px;
  opacity: 0;
  font-weight: 500;
  transform: translateX(-50%);
  transition: bottom 0.3s ease, opacity 0.3s ease;
}
.icon:hover .text {
  bottom: -35px;
  opacity: 1;
}

.icon:hover .layer span:nth-child(1) {
  opacity: 0.2;
}
.icon:hover .layer span:nth-child(2) {
  opacity: 0.4;
  transform: translate(5px, -5px);
}
.icon:hover .layer span:nth-child(3) {
  opacity: 0.6;
  transform: translate(10px, -10px);
}
.icon:hover .layer span:nth-child(4) {
  opacity: 0.8;
  transform: translate(15px, -15px);
}
.icon:hover .layer span:nth-child(5) {
  opacity: 1;
  transform: translate(20px, -20px);
}

.layer span.fab {
  font-size: 30px;
  line-height: 64px;
  text-align: center;
  fill: #4267b2;
  background: #000;
}
.user {
  display: flex;
  gap: 10px;
}
.img {
  width: 50px;
  height: 50px;
  font-size: 25px;
  font-weight: 700;
  border: 1px solid #4267b2;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #fff;
}
.name {
  font-size: 17px;
  font-weight: 700;
  color: #4267b2;
}
.details {
  display: flex;
  flex-direction: column;
  gap: 0;
  color: #fff;
}
.about {
  color: #ccc;
  padding-top: 5px;
}
</style>
