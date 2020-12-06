export function CheckHex(inputString: string) {
    let regExp = new RegExp('^[0-9A-Fa-f]{24}$')
    return regExp.test(inputString);
}
