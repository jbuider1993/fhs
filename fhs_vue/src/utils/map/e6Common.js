const getPageSize = function() {
  try {
    var xScroll; //页面滚动宽度
    var yScroll; //页面滚动高度
    var pageHeight;
    var pageWidth;

    if (window.innerHeight && window.scrollMaxY) {
      xScroll = document.body.scrollWidth;
      yScroll = window.innerHeight + window.scrollMaxY;
    } else if (document.body.scrollHeight > document.body.offsetHeight) {
      xScroll = document.body.scrollWidth;
      yScroll = document.body.scrollHeight;
    } else {
      xScroll = document.body.offsetWidth;
      yScroll = document.body.offsetHeight;
    }

    var windowWidth; //屏幕宽度
    var windowHeight; //屏幕高度

    if (self.innerHeight) {
      // all except Explorer
      windowWidth = self.innerWidth;
      windowHeight = self.innerHeight;
    } else if (
      document.documentElement &&
      document.documentElement.clientHeight
    ) {
      // Explorer 6 Strict Mode
      windowWidth = document.documentElement.clientWidth;
      windowHeight = document.documentElement.clientHeight;
    } else if (document.body) {
      // other Explorers
      windowWidth = document.body.clientWidth;
      windowHeight = document.body.clientHeight;
    }
    /*当页面滚动高度比屏幕高度小的时候，页面高度为屏幕高度，反之，为页面滚动高度*/
    if (yScroll < windowHeight) {
      pageHeight = windowHeight;
    } else {
      pageHeight = yScroll;
    }
    /*当页面滚动宽度比屏幕宽度小的时候，页面宽度为屏幕宽度，反之，为页面滚动宽度*/
    if (xScroll < windowWidth) {
      pageWidth = windowWidth;
    } else {
      pageWidth = xScroll;
    }

    var arrayPageSize = {
      pageWidth: pageWidth,
      pageHeight: pageHeight,
      windowWidth: windowWidth,
      windowHeight: windowHeight
    };
    return arrayPageSize;
  } catch (ex) {
    console.log("GetPageSize", ex);
  }
};

module.exports = {
  getPageSize
};
