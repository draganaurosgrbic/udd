import { Advertisement } from "./advertisement";

export interface ApplicationUpload {
    advertisementId: number;
    firstName: string;
    lastName: string;
    email: string;
    address: string;
    education: string;
    educationLevel: number;
    lat: number;
    lng: number;
    cvFile: Blob;
    letterFile: Blob;
}

export interface Application {
    id: number;
    advertisement: Advertisement;
    firstName: string;
    lastName: string;
    email: string;
    address: string;
    education: string;
    educationLevel: number;
    cvLocation: string;
    letterLocation: string;
}
