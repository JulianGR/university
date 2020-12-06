import {Injectable} from '@angular/core';
import * as k8s from '@kubernetes/client-node';

@Injectable({
  providedIn: 'root'
})
export class KubernetesClientService {

  kc: any
  k8sApi: any

  constructor() {
    this.kc = new k8s.KubeConfig();
    this.kc.loadFromDefault();
    this.k8sApi = this.kc.makeApiClient(k8s.CoreV1Api);

    /*const pod = {
    } as k8s.V1Pod;
     */
  }

  listNamespacedPod() {
    this.k8sApi.listNamespacedPod('default')
      .then((res: any) => {
        // tslint:disable-next-line:no-console
        console.log(res.body);
      });
  }


}
