export function dbFieldConversion(type) {
  if (type.indexOf('int') !== -1) {
    return 'Number'
  }
  if (type.indexOf('varchar') !== -1) {
    return 'String'
  }
  if (type.indexOf('date') !== -1) {
    return 'Date'
  }
}

export function getDbOperator() {
  return [
    {
      label: '=',
      value: '=',
      type: 'StringNumberDate'
    },
    {
      label: '!=',
      value: '!=',
      type: 'StringNumberDate'
    },
    {
      label: '>',
      value: '>',
      type: 'NumberDate'
    },
    {
      label: '<',
      value: '<',
      type: 'NumberDate'
    },
    {
      label: '>=',
      value: '>=',
      type: 'NumberDate'
    },
    {
      label: '=<',
      value: '=<',
      type: 'NumberDate'
    },
    {
      label: 'in',
      value: 'in',
      type: 'Number'
    },
    {
      label: 'like',
      value: 'like',
      type: 'String'
    },
    {
      label: 'between',
      value: 'between',
      type: 'NmuberDate'
    },
    {
      label: 'isNull',
      value: 'isNull',
      type: 'StringNumberDate'
    },
    {
      label: 'isNotNull',
      value: 'isNotNull',
      type: 'StringNumberDate'
    }
  ]
}
