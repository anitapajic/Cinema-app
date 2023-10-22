export const generateUniqueId = () => {
    return (Date.now() + Math.random()).toString(36);
};