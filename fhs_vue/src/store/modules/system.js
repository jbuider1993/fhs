const system = {
  state: {
    isFullScreen: false
  },
  mutations: {
    updateFullScreen(state, payload) {
      state.isFullScreen = !state.isFullScreen;
    }
  },
  actions: {}
};

export default system;
