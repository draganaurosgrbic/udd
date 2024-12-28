export interface ApplicationSearch {
    queries: SimpleQuery[]
}

export interface ApplicationGeoSearch {
    lat: number;
    lng: number;
    distance: number;
    unit: string;
}

export interface ApplicationSearchResult {
    adTitle: string;
    firstName: string;
    lastName: string;
    email: string;
    address: string;
    education: string;
    educationLevel: number;
    cvLocation: string;
    letterLocation: string;
    cvText: string;
    letterText: string;
}

export interface SimpleQuery {
    field: 'firstName' | 'lastName' | 'education' | 'educationLevel' | 'cvText' | 'letterText',
    value: string
    startValue: number
    endValue: number
    operator: 'AND' | 'OR'
    not: boolean
}
