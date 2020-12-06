export function CheckFreeRoom(inputString: string) {
    let regExp = new RegExp('^free[0-9]{4}$')
    return regExp.test(inputString);
}
