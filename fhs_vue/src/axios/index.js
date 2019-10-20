
let gateway =  process.env.VUE_APP_GATEWAY;
const IndividuationService = {
    options: {
        timeout: 100 * 1000,
        baseURL: gateway
    },
};

export default IndividuationService;

