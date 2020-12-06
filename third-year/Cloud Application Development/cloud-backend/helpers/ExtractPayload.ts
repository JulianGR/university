export function ExtractPayload(token: any) {
    const [encHeader, encPayload, signature] = token?.split('.')!;
    const payloadString = atob(encPayload);
    return JSON.parse(payloadString);
}
